package com.quipper.android.apollofrontpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.quipper.android.apollofrontpage.databinding.PostListFragmentBinding
import com.quipper.android.apollofrontpage.fragment.PostDetails
import org.koin.android.ext.android.inject

class PostListFragment : Fragment(), PostListAdapter.PostListHandler {

    companion object {
        fun newInstance() = PostListFragment()
    }

    private lateinit var binding: PostListFragmentBinding
    private val viewModel: PostListViewModel by inject()
    private var postListAdapter: PostListAdapter = PostListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.post_list_fragment,
            container,
            false
        )
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
