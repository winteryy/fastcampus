package winterry.part3.pagingprac3.new_network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import winterry.part3.pagingprac3.data.PassengerResponse
import winterry.part3.pagingprac3.new_data.GithubResponse

interface GithubApi {

////https://api.github.com/search/repositories?q=android?page=1&per_page=90

    @GET("search/repositories")
    suspend fun getData(
        @Query("q") q: String = "android",
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Response<GithubResponse>
}