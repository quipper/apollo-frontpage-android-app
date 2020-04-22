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
import com.apollographql.apollo.cache.normalized.sql.SqlNormalizedCacheFactory
import com.apollographql.apollo.exception.ApolloException
import com.quipper.android.apollofrontpage.fragment.PostDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostListViewModel(application: Application) : AndroidViewModel(application) {

    val data = MutableLiveData<List<PostDetails>>()

    private val cacheFactory =
        SqlNormalizedCacheFactory(getApplication<Application>(), name = "db_name")
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
}
