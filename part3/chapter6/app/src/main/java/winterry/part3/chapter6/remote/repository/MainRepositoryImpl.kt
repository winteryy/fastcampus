package winterry.part3.chapter6.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import winterry.part3.chapter6.model.ListItem
import winterry.part3.chapter6.remote.MainPagingSource
import winterry.part3.chapter6.remote.MainService
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
     private val mainService: MainService
) : MainRepository {
    override fun loadList() = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            MainPagingSource(mainService)
        }
    ).flow
}