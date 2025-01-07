package life.league.challenge.kotlin.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import life.league.challenge.kotlin.main.PostRepository
import life.league.challenge.kotlin.main.SessionManager
import life.league.challenge.kotlin.model.Post

class GetPostsUseCase(
    private val postRepository: PostRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(): List<Post> = withContext(Dispatchers.IO) {
        if (sessionManager.fetchAuthToken() == null) return@withContext emptyList()
        val users = postRepository.getUsers()
        val posts = postRepository.getPosts()

        return@withContext posts.map { userPost ->
            val user = users.first { user -> user.id == userPost.userId }
            Post(
                id = userPost.id,
                name = user.name,
                avatar = user.avatar,
                title = userPost.title,
                body = userPost.body
            )
        }
    }
}