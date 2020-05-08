package com.quipper.android.apollofrontpage

import androidx.lifecycle.*
import com.quipper.android.apollofrontpage.fragment.PostDetails
import com.quipper.android.apollofrontpage.repository.PostsRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PostListViewModel(
    private val postsRepository: PostsRepository
) : ViewModel() {

    private val _postsData = MutableLiveData<List<PostDetails>>()
    val postsData: LiveData<List<PostDetails>>
        get() = _postsData.distinctUntilChanged()

    init {
        viewModelScope.launch {
            postsRepository.getPosts()
                .collect { _postsData.value = it }
        }
    }

    fun upVote(postId: Int) {
        viewModelScope.launch {
            postsRepository.upVote(postId)
                .collect { data ->
                    _postsData.postValue(_postsData.value?.map {
                        if (it.id == postId)
                            it.copy(votes = data?.upvotePost?.votes)
                        else it
                    })
                }
        }
    }
}