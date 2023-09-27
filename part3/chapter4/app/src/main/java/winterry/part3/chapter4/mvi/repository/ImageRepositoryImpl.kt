package winterry.part3.chapter4.mvi.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import winterry.part3.chapter4.RetrofitManager
import winterry.part3.chapter4.mvi.model.Image

class ImageRepositoryImpl(private val dispatcher: CoroutineDispatcher = Dispatchers.IO): ImageRepository {

    override suspend fun getRandomImage() = withContext(dispatcher) {
        RetrofitManager.imageService.getRandomImageSuspend().let {
            Image(it.urls.regular, it.color)
        }
    }


}
