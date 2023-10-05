package winterry.part3.chapter6.remote

import retrofit2.http.GET
import retrofit2.http.Query
import winterry.part3.chapter6.model.NetworkResponse

interface MainService {

    @GET("chapter6")
    suspend fun getList(
        @Query("page") page: Int,
        @Query("size") size: Int = 20,
    ): NetworkResponse
}