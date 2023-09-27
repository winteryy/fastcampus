package winterry.part3.chapter4.mvvm.repository

import io.reactivex.Single
import winterry.part3.chapter4.mvvm.model.Image

interface ImageRepository {

    fun getRandomImage(): Single<Image>
}