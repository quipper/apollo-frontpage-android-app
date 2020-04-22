package com.quipper.android.apollofrontpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.quipper.android.apollofrontpage.databinding.PostListFragmentBinding
import com.quipper.android.apollofrontpage.fragment.PostDetails

class PostListFragment : Fragment(), PostListAdapter.PostListHandler {

    companion object {
        fun newInstance() = PostListFragment()
    }

    private lateinit var binding: PostListFragmentBinding
    private lateinit var viewModel: PostListViewModel
    private val controller by lazy { PostListItemController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.post_list_fragment,
            container,
            false
        )
        initViews()

        viewModel = ViewModelProviders.of(this).get(PostListViewModel::class.java)
        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            binding.refresh.isRefreshing = false
            controller.setData(it)
        })
        viewModel.fetch()


        return binding.root
    }

    private fun initViews() {
        binding.postList.adapter = controller.adapter
        binding.refresh.setOnRefreshListener {
            controller.setData(emptyList())
            viewModel.fetch()

        }
    }

    override fun handle(details: PostDetails) {
    }
}

data class Item(
    val id: Int,
    val title: String?
)
