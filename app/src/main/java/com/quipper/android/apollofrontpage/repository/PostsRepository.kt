package com.quipper.android.apollofrontpage.repository

import com.quipper.android.apollofrontpage.model.Posts

interface PostsRepository {
    //TODO Change return type if required
    fun getPosts(): List<Posts>
}