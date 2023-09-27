package winterry.part3.chapter4.mvp.repository

import retrofit2.Call
import retrofit2.Response
import winterry.part3.chapter4.ImageResponse
import winterry.part3.chapter4.RetrofitManager

class ImageRepositoryImpl: ImageRepository {
    override fun getRandomImage(callback: ImageRepository.Callback) {
        RetrofitManager.imageService.getRandomImage()
            .enqueue(object: retrofit2.Callback<ImageResponse> {
                override fun onResponse(
                    call: Call<ImageResponse>,
                    response: Response<ImageResponse>
                ) {
                    if(response.isSuccessful) {
                        response.body()?.let {
                            callback.loadImage(it.urls.regular, it.color)
                        }
                    }
                }

                override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                    t.printStackTrace()
                }

            })
    }
}