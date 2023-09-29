package winterry.part3.chapter5

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import winterry.part3.chapter5.model.ImageListResponse
import winterry.part3.chapter5.model.VideoListResponse

interface SearchService {

    @Headers("Authorization: KakaoAK e8bbfed40a349e0b83464ffe3549bf55")
    @GET("image")
    fun searchImage(@Query("query") query: String): Observable<ImageListResponse>

    @Headers("Authorization: KakaoAK e8bbfed40a349e0b83464ffe3549bf55")
    @GET("vclip")
    fun searchVideo(@Query("query") query: String): Observable<VideoListResponse>
}