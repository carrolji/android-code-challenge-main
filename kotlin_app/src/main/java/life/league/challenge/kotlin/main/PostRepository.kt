package life.league.challenge.kotlin.main

import kotlinx.coroutines.runBlocking
import life.league.challenge.kotlin.api.Api
import life.league.challenge.kotlin.api.login
import life.league.challenge.kotlin.model.PostResponse
import life.league.challenge.kotlin.model.UserResponse
import retrofit2.HttpException

interface PostRepository {
    suspend fun login(userName: String, password: String): String?
    suspend fun getUsers(): List<UserResponse>
    suspend fun getPosts(): List<PostResponse>
}

class PostRepositoryImpl(
    private val service: Api,
    private val sessionManager: SessionManager
) : PostRepository {

    override suspend fun login(userName: String, password: String): String? {
        return runBlocking {
            val response = service.login(userName, password)
            if (response.isSuccessful) {
                val token = response.body()?.apiKey
                if (token != null) {
                    sessionManager.saveAuthToken(token)
                }
                token
            } else {
                throw HttpException(response)
            }
        }
    }

    override suspend fun getUsers(): List<UserResponse> {
        return runBlocking {
            val userResponse = service.getUsers()
            if (userResponse.isSuccessful) {
                userResponse.body() ?: emptyList()
            } else {
                throw HttpException(userResponse)
            }
        }
    }

    override suspend fun getPosts(): List<PostResponse> {
        return runBlocking {
            val response = service.getPosts()
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                throw HttpException(response)
            }
        }
    }
}