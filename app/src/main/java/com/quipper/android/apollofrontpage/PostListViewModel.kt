package com.quipper.android.apollofrontpage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toFlow
import com.quipper.android.apollofrontpage.fragment.PostDetails
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient

class PostListViewModel(

) : ViewModel() {
    val liveData: MutableLiveData<List<PostDetails>> = MutableLiveData()

    val apolloClient by lazy {
        ApolloClient.builder()
            .serverUrl("http://10.0.2.2:8080/graphql")
            .okHttpClient(OkHttpClient().newBuilder().build())
            .build()
    }

    @ExperimentalCoroutinesApi
    fun fetch() {
        viewModelScope.launch {
            apolloClient.query(
                AllPostsQuery()
            )
                .toFlow()
                .collect {
                    liveData.value = it.data()?.posts?.map {
                        it.fragments.postDetails
                    }
                }
        }
    }

}
