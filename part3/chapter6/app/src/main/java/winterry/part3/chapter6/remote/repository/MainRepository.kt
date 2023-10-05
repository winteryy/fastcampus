package winterry.part3.chapter6.remote.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import winterry.part3.chapter6.model.ListItem

interface MainRepository {

    fun loadList(): Flow<PagingData<ListItem>>
}