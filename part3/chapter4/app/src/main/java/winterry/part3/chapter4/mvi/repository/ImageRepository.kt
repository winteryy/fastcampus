package winterry.part3.chapter4.mvi.repository

import winterry.part3.chapter4.mvi.model.Image

interface ImageRepository {

    suspend fun getRandomImage(): Image
}