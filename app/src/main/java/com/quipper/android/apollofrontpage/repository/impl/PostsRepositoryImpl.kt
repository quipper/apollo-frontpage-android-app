package com.quipper.android.apollofrontpage.repository.impl

import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.quipper.android.apollofrontpage.AllPostsQuery
import com.quipper.android.apollofrontpage.fragment.PostDetails
import com.quipper.android.apollofrontpage.model.PostsResult
import com.quipper.android.apollofrontpage.repository.PostsRepository

class PostsRepositoryImpl : PostsRepository {
    internal var resource = MutableLiveData<List<PostDetails>>()
    internal var error = MutableLiveData<ApolloException>()

    override fun getPosts(): PostsResult {
        val client = ApolloClient.builder().serverUrl("http://10.0.2.2:8080/graphql").build()
        client.query(AllPostsQuery())
            .enqueue(object : ApolloCall.Callback<AllPostsQuery.Data>() {
                override fun onFailure(exception: ApolloException) {
                    error.postValue(exception)
                }

                override fun onResponse(response: Response<AllPostsQuery.Data>) {
                    resource.postValue(response.data()?.posts?.map { it.fragments.postDetails })
                }
            })
        return PostsResult(
            resource,
            error
        )
    }
}