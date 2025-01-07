package life.league.challenge.kotlin.main

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import life.league.challenge.kotlin.MainCoroutineRule
import life.league.challenge.kotlin.model.Post
import life.league.challenge.kotlin.usecase.GetPostsUseCase
import life.league.challenge.kotlin.usecase.LoginUseCase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private val loginUseCase: LoginUseCase = mockk<LoginUseCase>()
    private val getPostsUseCase: GetPostsUseCase = mockk<GetPostsUseCase>()
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        viewModel = MainViewModel(loginUseCase, getPostsUseCase)
    }

    @Test
    fun `calls login user input user name and password and returns token`() {
        coEvery { loginUseCase("hello","world") } returns "TOKEN"

        viewModel.login("hello", "world")

        coVerify { loginUseCase("hello","world") }
    }

    @Test
    fun `calls login empty user name and password and returns token`() {
        coEvery { loginUseCase("", "") } returns "TOKEN"

        viewModel.login()

        coVerify { loginUseCase("", "") }
    }

    @Test
    fun `calls login and get posts`() {
        val username = "hello"
        val password = "world"
        val mockPost = Post(1)
        coEvery { loginUseCase(username,password) } returns "TOKEN"
        coEvery { getPostsUseCase() } returns listOf(mockPost)

        viewModel.login(username, password)
        assertEquals(listOf(mockPost), viewModel.postList.value)
        assertEquals(1, viewModel.postList.value.size)

        coVerify { loginUseCase(username,password) }
        coVerify { getPostsUseCase() }
    }
}