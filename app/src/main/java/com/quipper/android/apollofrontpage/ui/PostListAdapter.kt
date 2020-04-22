package com.quipper.android.apollofrontpage.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.quipper.android.apollofrontpage.databinding.PostListItemBinding
import com.quipper.android.apollofrontpage.fragment.PostDetails

class PostListAdapter(private val handler: PostListHandler) :
    ListAdapter<PostDetails, PostListAdapter.ViewHolder>(
        DIFF_CALLBACK
    ) {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PostDetails>() {

            override fun areItemsTheSame(oldItem: PostDetails, newItem: PostDetails): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PostDetails, newItem: PostDetails): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PostListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    inner class ViewHolder(private val binding: PostListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val details = getItem(adapterPosition)
            binding.apply {
                data = details
                root.setOnClickListener { handler.handle(details) }
                executePendingBindings()
            }
        }
    }

    interface PostListHandler {
        fun handle(details: PostDetails)
    }
}
