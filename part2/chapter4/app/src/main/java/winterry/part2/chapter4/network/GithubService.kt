package winterry.part2.chapter4.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import winterry.part2.chapter4.model.Repo
import winterry.part2.chapter4.model.UserDto

interface GithubService {

    @Headers("Authorization: Bearer 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")
    @GET("/users/{username}/repos")
    fun listRepo(@Path("username") username: String): Call<List<Repo>>

    @Headers("Authorization: Bearer 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")
    @GET("/search/users")
    fun searchUsers(@Query("q") query: String): Call<UserDto>

}