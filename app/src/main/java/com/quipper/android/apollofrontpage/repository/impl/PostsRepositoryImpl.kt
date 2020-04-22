package com.quipper.android.apollofrontpage.repository.impl

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.ApolloClient
import com.quipper.android.apollofrontpage.AllPostsQuery
import com.quipper.android.apollofrontpage.fragment.PostDetails
import com.quipper.android.apollofrontpage.model.PostsResult
import com.quipper.android.apollofrontpage.repository.PostsRepository
import com.quipper.android.apollofrontpage.util.ApolloRxHelper
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers.io

class PostsRepositoryImpl(
    private val apolloRxHelper: ApolloRxHelper,
    private val apolloClient: ApolloClient
) : PostsRepository {
    private var resource = MutableLiveData<List<PostDetails>>()
    private var error = MutableLiveData<Throwable>()

    @SuppressLint("CheckResult")
    override fun getPosts(): PostsResult {
        apolloRxHelper.from(apolloClient.query(AllPostsQuery()))
            .subscribeOn(io())
            .observeOn(io())
            .flatMap { dataResponse -> Observable.fromArray(dataResponse.data()) }
            .subscribe({ data ->
                resource.postValue(data?.posts?.map { it.fragments.postDetails })
            }, {
                error.postValue(it)
            })

        return PostsResult(resource, error)
    }
}