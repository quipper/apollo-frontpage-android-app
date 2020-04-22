package com.quipper.android.apollofrontpage.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.quipper.android.apollofrontpage.R
import com.quipper.android.apollofrontpage.databinding.PostListFragmentBinding
import com.quipper.android.apollofrontpage.fragment.PostDetails
import org.koin.android.ext.android.inject

class PostListFragment : Fragment(), PostListAdapter.PostListHandler {

    companion object {
        fun newInstance() =
            PostListFragment()
    }

    private lateinit var binding: PostListFragmentBinding
    private val viewModel: PostListViewModel by inject()
    private var postListAdapter: PostListAdapter =
        PostListAdapter(this)

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

        viewModel.postResult.posts.observe(viewLifecycleOwner, Observer {
            it ?: return@Observer
            postListAdapter.submitList(it)
        })

        viewModel.postResult.error.observe(viewLifecycleOwner, Observer {
            it ?: return@Observer
            Toast.makeText(
                requireContext(),
                resources.getString(R.string.common_error),
                Toast.LENGTH_SHORT
            ).show()
        })
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
    }

    override fun handle(details: PostDetails) {
    }
}
