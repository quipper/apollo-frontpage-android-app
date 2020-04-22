package com.quipper.android.apollofrontpage

import com.airbnb.epoxy.TypedEpoxyController
import com.quipper.android.apollofrontpage.fragment.PostDetails

class PostListItemController : TypedEpoxyController<List<PostDetails>>(){
    override fun buildModels(data: List<PostDetails>) {
        data.forEach {
            postListItem {
                id(it.id)
                title(it.title)
            }
        }
    }
}