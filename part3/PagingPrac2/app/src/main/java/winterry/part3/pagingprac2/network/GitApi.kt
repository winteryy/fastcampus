package winterry.part3.pagingprac2.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import winterry.part3.pagingprac2.data.GithubResponse

interface GitApi {

    //https://api.github.com/users/google/repos?page=1&per_page=90

    @GET("users/google/repos")
    suspend fun getData(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
    ): Response<GithubResponse>
}