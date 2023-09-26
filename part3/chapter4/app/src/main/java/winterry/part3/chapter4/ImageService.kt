package winterry.part3.chapter4

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ImageService {

    @Headers("Authorization: Client-ID zeGpA5lWLBt5WWC9YeN65z-chAQrVxIKv-11unJYfPU")
    @GET("/photos/random")
    fun getRandomImage() : Call<ImageResponse>
}