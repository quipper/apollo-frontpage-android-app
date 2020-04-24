package com.quipper.android.apollofrontpage

import androidx.lifecycle.ViewModel
import com.quipper.android.apollofrontpage.model.PostsResult
import com.quipper.android.apollofrontpage.repository.PostsRepository

class PostListViewModel(
    private val postsRepository: PostsRepository
) : ViewModel() {

    val postsResult: PostsResult
        get() = postsRepository.getPosts()
}
