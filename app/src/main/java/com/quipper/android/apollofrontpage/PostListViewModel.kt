package com.quipper.android.apollofrontpage

import androidx.lifecycle.*
import com.quipper.android.apollofrontpage.fragment.PostDetails
import com.quipper.android.apollofrontpage.repository.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostListViewModel(
    private val postsRepository: PostsRepository
) : ViewModel() {

    private val _postsData = MediatorLiveData<List<PostDetails>>()
    val postsData: LiveData<List<PostDetails>>
        get() = _postsData.distinctUntilChanged()

    private var _errorData = MutableLiveData<Throwable>()
    val errorData: LiveData<Throwable>
        get() = _errorData.distinctUntilChanged()

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            postsRepository.getPosts()
                .subscribe({ data ->
                    _postsData.postValue(data?.posts?.map { it.fragments.postDetails })
                }, {
                    _errorData.postValue(it)
                })
        }
    }

    fun upVote(postId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            postsRepository.upVote(postId)
                .subscribe({ data ->
                    _postsData.postValue(
                        _postsData.value?.map {
                            if (it.id == postId)
                                it.copy(votes = data.data()?.upvotePost?.votes)
                            else it
                        }
                    )
                }, {
                    _errorData.postValue(it)
                })
        }
    }
}