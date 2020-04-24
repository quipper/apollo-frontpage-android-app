package com.quipper.android.apollofrontpage.model

import androidx.lifecycle.LiveData
import com.apollographql.apollo.exception.ApolloException
import com.quipper.android.apollofrontpage.fragment.PostDetails

data class PostsResult(
    var postsData: LiveData<List<PostDetails>>,
    var errorData: LiveData<Throwable>
)