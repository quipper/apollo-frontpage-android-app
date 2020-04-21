package com.quipper.android.apollofrontpage

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.quipper.android.apollofrontpage.databinding.PostListFragmentBinding
import com.quipper.android.apollofrontpage.fragment.PostDetails

class PostListFragment : Fragment(), PostListAdapter.PostListHandler {

    companion object {
        fun newInstance() = PostListFragment()
    }

    private lateinit var binding: PostListFragmentBinding
    private lateinit var viewModel: PostListViewModel
    private var postListAdapter: PostListAdapter = PostListAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.post_list_fragment,
            container,
            false
        )
        viewModel = ViewModelProviders.of(this).get(PostListViewModel::class.java)
        initViews()
        return binding.root
    }

    private fun initViews() {
        binding.postList.apply {
            adapter = postListAdapter
        }
    }

    override fun handle(details: PostDetails) {
    }
}
