package com.quipper.android.apollofrontpage.repository.impl

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toFlow
import com.quipper.android.apollofrontpage.AllPostsQuery
import com.quipper.android.apollofrontpage.UpvotePostMutation
import com.quipper.android.apollofrontpage.fragment.PostDetails
import com.quipper.android.apollofrontpage.repository.PostsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalCoroutinesApi::class)
class PostsRepository(
    private val apolloClient: ApolloClient
) : PostsRepository {

    override fun getPosts(): Flow<List<PostDetails>?> =
        apolloClient.query(AllPostsQuery())
            .toFlow()
            .map { response ->
                response.data()?.posts?.map {
                    it.fragments.postDetails
                }
            }

    override fun upVote(postId: Int): Flow<UpvotePostMutation.Data?> =
        apolloClient.mutate(UpvotePostMutation(postId))
            .toFlow()
            .map { response -> response.data() }
}