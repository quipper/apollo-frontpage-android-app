package com.quipper.android.apollofrontpage.model

import androidx.lifecycle.LiveData
import com.apollographql.apollo.exception.ApolloException
import com.quipper.android.apollofrontpage.fragment.PostDetails

data class PostsResult(
    var posts: LiveData<List<PostDetails>>,
    var error: LiveData<Throwable>
)