package com.quipper.android.apollofrontpage.repository.impl

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx2.rxMutate
import com.apollographql.apollo.rx2.rxQuery
import com.quipper.android.apollofrontpage.AllPostsQuery
import com.quipper.android.apollofrontpage.UpvotePostMutation
import com.quipper.android.apollofrontpage.repository.PostsRepository
import io.reactivex.Observable
import io.reactivex.Single

class PostsRepository(
    private val apolloClient: ApolloClient
) : PostsRepository {

    override fun getPosts(): Single<AllPostsQuery.Data> {
        return Single.fromObservable(
            apolloClient.rxQuery(AllPostsQuery())
                .flatMap { dataResponse -> Observable.fromArray(dataResponse.data()) })
    }

    override fun upVote(postId: Int): Single<Response<UpvotePostMutation.Data>> {
        return apolloClient.rxMutate(UpvotePostMutation(postId))
    }
}