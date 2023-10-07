package winterry.part3.pagingprac3.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import winterry.part3.pagingprac3.data.PassengerResponse

interface PassengerApi {

    //https://api.instantwebtools.net/v1/passenger?page=1&size=90

    @GET("passenger")
    suspend fun getData(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<PassengerResponse>
}