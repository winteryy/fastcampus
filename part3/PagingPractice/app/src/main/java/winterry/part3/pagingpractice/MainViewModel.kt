package winterry.part3.pagingpractice

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class MainViewModel: ViewModel() {

    val items: Flow<PagingData<User>> = Pager(
        config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            MyPagingSource()
        }
    ).flow
}