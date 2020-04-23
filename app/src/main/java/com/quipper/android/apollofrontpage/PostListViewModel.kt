package com.quipper.android.apollofrontpage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.cache.normalized.CacheKey
import com.apollographql.apollo.cache.normalized.CacheKeyResolver
import com.apollographql.apollo.cache.normalized.lru.EvictionPolicy
import com.apollographql.apollo.cache.normalized.lru.LruNormalizedCacheFactory
import com.apollographql.apollo.exception.ApolloException
import com.quipper.android.apollofrontpage.fragment.PostDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostListViewModel(application: Application) : AndroidViewModel(application) {

    val data = MutableLiveData<List<PostDetails>>()

    private val cacheFactory =
        LruNormalizedCacheFactory(EvictionPolicy.builder().maxSizeBytes(10 * 1024).build())
    private val resolver: CacheKeyResolver = object : CacheKeyResolver() {

        override fun fromFieldRecordSet(
            field: ResponseField,
            recordSet: Map<String, Any>
        ): CacheKey {
            return formatCacheKey(recordSet["id"] as? String?)
        }

        override fun fromFieldArguments(
            field: ResponseField,
            variables: Operation.Variables
        ): CacheKey {
            return formatCacheKey(field.resolveArgument("id", variables) as String?)
        }

        private fun formatCacheKey(id: String?) = when {
            id.isNullOrEmpty() -> CacheKey.NO_KEY
            else -> CacheKey.from(id)
        }
    }
    private val client = ApolloClient.builder()
        .normalizedCache(cacheFactory, resolver)
        .serverUrl("http://10.0.2.2:8080/graphql")
        .build()

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            client.query(AllPostsQuery())
                .enqueue(object : ApolloCall.Callback<AllPostsQuery.Data>() {

                    override fun onFailure(e: ApolloException) = Unit

                    override fun onResponse(response: Response<AllPostsQuery.Data>) {
                        data.postValue(response.data?.posts?.map { it.fragments.postDetails })
                    }
                })
        }
    }

    fun increaseVote(postId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            client.mutate(UpvotePostMutation(postId))
                .enqueue(object : ApolloCall.Callback<UpvotePostMutation.Data>() {

                    override fun onFailure(e: ApolloException) = Unit

                    override fun onResponse(response: Response<UpvotePostMutation.Data>) {
                        data.postValue(
                            data.value?.map {
                                if (it.id == postId)
                                    it.copy(votes = response.data?.upvotePost?.votes)
                                else it
                            }
                        )
                    }
                })
        }
    }
}
