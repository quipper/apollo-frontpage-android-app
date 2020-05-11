package com.quipper.android.apollofrontpage.repository

import com.apollographql.apollo.api.Response
import com.quipper.android.apollofrontpage.AllPostsQuery
import com.quipper.android.apollofrontpage.UpvotePostMutation
import io.reactivex.Single

interface PostsRepository {
    fun getPosts(): Single<AllPostsQuery.Data>
    fun upVote(postId: Int): Single<Response<UpvotePostMutation.Data>>
}