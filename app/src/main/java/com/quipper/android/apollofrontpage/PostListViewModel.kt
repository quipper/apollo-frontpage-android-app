package com.quipper.android.apollofrontpage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.quipper.android.apollofrontpage.fragment.PostDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostListViewModel : ViewModel() {
    val data = MutableLiveData<List<PostDetails>>()

    fun fetchData() {
        val client = ApolloClient.builder().serverUrl("http://10.0.2.2:8080/graphql").build()
        viewModelScope.launch(Dispatchers.IO) {
            client.query(AllPostsQuery())
                .enqueue(object : ApolloCall.Callback<AllPostsQuery.Data>() {
                    override fun onFailure(e: ApolloException) {
                        Log.e("hoge", "fuga", e)
                    }

                    override fun onResponse(response: Response<AllPostsQuery.Data>) {
                        data.postValue(response.data()?.posts?.map { it.fragments.postDetails })
                    }
                })
        }
    }
}
