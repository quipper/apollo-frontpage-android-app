package com.quipper.android.apollofrontpage.repository

import com.apollographql.apollo.api.Response
import com.quipper.android.apollofrontpage.AllPostsQuery
import com.quipper.android.apollofrontpage.UpvotePostMutation
import io.reactivex.Observable
import io.reactivex.Single

interface PostsRepository {
    fun getPosts(): Observable<Response<AllPostsQuery.Data>>
    fun upVote(postId: Int): Single<Response<UpvotePostMutation.Data>>
}