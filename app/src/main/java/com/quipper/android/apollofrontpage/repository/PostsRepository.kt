package com.quipper.android.apollofrontpage.repository

import com.quipper.android.apollofrontpage.model.PostsResult

interface PostsRepository {
    fun getPosts(): PostsResult
    fun upVote(postId: Int): PostsResult
}