package life.league.challenge.kotlin

import android.util.Base64
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.runBlocking
import life.league.challenge.kotlin.api.Api
import life.league.challenge.kotlin.api.login
import life.league.challenge.kotlin.main.PostRepositoryImpl
import life.league.challenge.kotlin.main.SessionManager
import life.league.challenge.kotlin.model.Account
import life.league.challenge.kotlin.model.PostResponse
import life.league.challenge.kotlin.model.UserResponse
import org.junit.Test
import retrofit2.Response.success
import kotlin.test.assertEquals

class PostRepositoryImplTest {

    private val mockService: Api = mockk()
    private val manager: SessionManager = mockk()
    private val postRepository = PostRepositoryImpl(mockService, manager)
    private val mockPostResponse = PostResponse(
        userId = 1,
        id = 1,
        title = "title",
        body = "body"
    )
    private val mockUserResponse = UserResponse(
        id = 1,
        name = "John",
        avatar = null,
        company = null,
        username = "username",
        email = "email",
        address = null,
        phone = null,
        website = null,
    )

    @Test
    fun `calls login service and return a token`(): Unit = runBlocking {
        mockkStatic(Base64::class)
        // GIVEN
        val username = "username"
        val password = "password"
        val accessToken = "TOKEN"
        val encodedCredentials = java.util.Base64.getEncoder().encodeToString("$username:$password".toByteArray())

        every {
            Base64.encodeToString("$username:$password".toByteArray(), Base64.NO_WRAP)
        } answers {
            encodedCredentials
        }
        coEvery { mockService.login("Basic $encodedCredentials") } returns success(Account(accessToken))
        coEvery { mockService.login(username, password) } returns success(Account(accessToken))
        coEvery { manager.saveAuthToken(accessToken) } returns Unit

        // WHEN
        val result = postRepository.login(username, password)

        //THEN
        assertEquals(accessToken, result)
    }

    @Test
    fun `calls get posts service success`() = runBlocking {
        // GIVEN
        coEvery { mockService.getPosts() } returns success(listOf(mockPostResponse))

        // WHEN
        val result = postRepository.getPosts()

        //THEN
        assertEquals(1, result.size)
    }

    @Test
    fun `calls get user success`() = runBlocking {
        // GIVEN
        coEvery { mockService.getUsers() } returns success(listOf(mockUserResponse))

        // WHEN
        val result = postRepository.getUsers()

        //THEN
        assertEquals(1, result.size)
    }

    @Test
    fun `calls get user - returns empty list`() = runBlocking {
        // GIVEN
        coEvery { mockService.getUsers() } returns success(listOf())

        // WHEN
        val result = postRepository.getUsers()

        //THEN
        assertEquals(emptyList(), result)
    }
}