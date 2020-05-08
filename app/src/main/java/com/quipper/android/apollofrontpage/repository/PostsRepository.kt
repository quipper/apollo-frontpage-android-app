package com.quipper.android.apollofrontpage.repository

import com.quipper.android.apollofrontpage.UpvotePostMutation
import com.quipper.android.apollofrontpage.fragment.PostDetails
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    fun getPosts(): Flow<List<PostDetails>?>
    fun upVote(postId: Int): Flow<UpvotePostMutation.Data?>
}