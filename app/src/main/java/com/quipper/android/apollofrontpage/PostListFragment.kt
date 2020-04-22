package com.quipper.android.apollofrontpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.quipper.android.apollofrontpage.databinding.PostListFragmentBinding
import com.quipper.android.apollofrontpage.fragment.PostDetails

class PostListFragment : Fragment(), PostListAdapter.PostListHandler {

    companion object {

        fun newInstance() = PostListFragment()
    }

    private lateinit var binding: PostListFragmentBinding
    private val viewModel: PostListViewModel by viewModels()
    private var postListAdapter: PostListAdapter = PostListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PostListFragmentBinding.inflate(inflater, container, false)
        initViews()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.data.observe(viewLifecycleOwner, Observer {
            it ?: return@Observer
            binding.refresh.isRefreshing = false
            postListAdapter.submitList(it)
        })
        viewModel.fetchData()
    }

    private fun initViews() {
        binding.postList.apply {
            adapter = postListAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        binding.refresh.setOnRefreshListener {
            viewModel.fetchData()
        }
    }

    override fun handle(details: PostDetails) {
    }
}
