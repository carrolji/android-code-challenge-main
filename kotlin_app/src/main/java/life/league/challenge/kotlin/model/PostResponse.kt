package life.league.challenge.kotlin.model

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("body")
    val body: String? = null,
)
