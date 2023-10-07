package winterry.part3.pagingprac4.network

import retrofit2.http.GET
import retrofit2.http.Query
import winterry.part3.pagingprac4.model.GithubResponse

interface GitApi {
    //https://api.github.com/search/repositories?q=android?page=1&per_page=90

    @GET("search/repositories")
    suspend fun getData(
        @Query("q") q: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): GithubResponse
}