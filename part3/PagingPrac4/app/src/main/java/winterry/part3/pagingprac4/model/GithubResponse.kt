package winterry.part3.pagingprac4.model

data class GithubResponse(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<Item>
)