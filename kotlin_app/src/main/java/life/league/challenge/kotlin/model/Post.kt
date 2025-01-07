package life.league.challenge.kotlin.model

data class Post(
    val id: Int,
    val name: String? = null,
    val avatar: String? = null,
    val title: String? = null,
    val body: String? = null,
)