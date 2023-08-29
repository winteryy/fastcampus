package winterry.part2.chapter4.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import winterry.part2.chapter4.model.Repo
import winterry.part2.chapter4.model.UserDto

interface GithubService {
    @GET("/users/{username}/repos")
    fun listRepo(@Path("username") username: String, @Query("page") page: Int): Call<List<Repo>>

    @GET("/search/users")
    fun searchUsers(@Query("q") query: String): Call<UserDto>

}