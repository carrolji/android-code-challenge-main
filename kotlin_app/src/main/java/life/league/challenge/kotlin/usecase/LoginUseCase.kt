package life.league.challenge.kotlin.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import life.league.challenge.kotlin.main.PostRepository

class LoginUseCase(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(userName: String, password: String): String? =
        withContext(Dispatchers.IO) { postRepository.login(userName, password) }
}