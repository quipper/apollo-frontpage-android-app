package com.quipper.android.apollofrontpage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.quipper.android.apollofrontpage.databinding.PostListItemBinding
import com.quipper.android.apollofrontpage.fragment.PostDetails

class PostListAdapter(private val handler: PostListHandler) :
    ListAdapter<PostDetails, PostListAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<PostListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.post_list_item, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val details = getItem(position)
        holder.bind(details)
        holder.set(View.OnClickListener {
            handler.handle(details)
        })
    }

    class ViewHolder(private val binding: PostListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(details: PostDetails) {
            binding.apply {
                title.text = details.title
                executePendingBindings()
            }
        }
        fun set(listener: View.OnClickListener) {
            binding.apply {
                title.setOnClickListener(listener)
                executePendingBindings()
            }
        }
    }

    interface PostListHandler {
        fun handle(details: PostDetails)
    }

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
}
