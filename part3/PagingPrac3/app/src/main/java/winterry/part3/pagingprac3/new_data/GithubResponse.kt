package winterry.part3.pagingprac3.new_data

import com.google.gson.annotations.SerializedName

data class GithubResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<NewItem>
)

data class NewItem(
    val id: Long,
    val name: String,
    val owner: Owner
)

data class Owner(
    @SerializedName("avatar_url") val avatarUrl: String
)