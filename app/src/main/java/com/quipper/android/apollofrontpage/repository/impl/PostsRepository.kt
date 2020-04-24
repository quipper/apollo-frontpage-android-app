package com.quipper.android.apollofrontpage.repository.impl

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx2.rxQuery
import com.quipper.android.apollofrontpage.AllPostsQuery
import com.quipper.android.apollofrontpage.fragment.PostDetails
import com.quipper.android.apollofrontpage.model.PostsResult
import com.quipper.android.apollofrontpage.repository.PostsRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers.io

class PostsRepository(
    private val apolloClient: ApolloClient
) : PostsRepository {
    private var posts = MutableLiveData<List<PostDetails>>()
    private var error = MutableLiveData<Throwable>()

    @SuppressLint("CheckResult")
    override fun getPosts(): PostsResult {
        apolloClient.rxQuery(AllPostsQuery()).subscribeOn(io())
            .observeOn(io())
            .flatMap { dataResponse -> Observable.fromArray(dataResponse.data()) }
            .subscribe({ data ->
                posts.postValue(data?.posts?.map { it.fragments.postDetails })
            }, {
                error.postValue(it)
            })
        return PostsResult(posts, error)
    }
}