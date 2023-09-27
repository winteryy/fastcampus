package winterry.part3.chapter4.mvi

import winterry.part3.chapter4.mvi.model.Image

sealed class MviState {
    object Idle: MviState()
    object Loading: MviState()
    data class LoadedImage(val image: Image, val count: Int): MviState()
}