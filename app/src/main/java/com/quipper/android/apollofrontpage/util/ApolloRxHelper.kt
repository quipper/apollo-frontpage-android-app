package com.quipper.android.apollofrontpage.util

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloQueryWatcher
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx2.Rx2Apollo
import io.reactivex.Observable

/**
 * A Wrapper class for Rx2Apollo static methods to improve testability
 */
class ApolloRxHelper {
    fun <T> from(watcher: ApolloQueryWatcher<T>): Observable<Response<T>> {
        return Rx2Apollo.from(watcher)
    }

    fun <T> from(call: ApolloCall<T>): Observable<Response<T>> {
        return Rx2Apollo.from(call)
    }
}