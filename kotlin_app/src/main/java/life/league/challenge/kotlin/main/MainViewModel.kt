package life.league.challenge.kotlin.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import life.league.challenge.kotlin.model.Post
import life.league.challenge.kotlin.usecase.GetPostsUseCase
import life.league.challenge.kotlin.usecase.LoginUseCase

class MainViewModel(
    private val loginUseCase: LoginUseCase,
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {

    private val _postList = MutableStateFlow<List<Post>>(emptyList())
    val postList: StateFlow<List<Post>>
        get() = _postList

    fun login(userName: String = "", password: String = "") = viewModelScope.launch {
        val authToken = loginUseCase(userName, password)
        if (authToken != null) {
            getPosts()
        }
    }

    private fun getPosts() = viewModelScope.launch {
        val posts = getPostsUseCase()
        _postList.value = posts
    }
}