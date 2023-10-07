package winterry.part3.pagingprac3.new_network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewRetrofitInstance {

    private const val BASE_URL = "https://api.github.com/"

    private val client = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getInstance(): Retrofit {
        return client
    }
}