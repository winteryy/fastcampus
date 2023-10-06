package winterry.part3.pagingprac2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import winterry.part3.pagingprac2.data.GithubResponseItem
import winterry.part3.pagingprac2.network.GitApi
import winterry.part3.pagingprac2.network.RetrofitInstance
import winterry.part3.pagingprac2.paging.MyPagingSource

class MainViewModel: ViewModel() {

    private val api = RetrofitInstance.getInstance().create(GitApi::class.java)

    val items: Flow<PagingData<GithubResponseItem>> = Pager(
        config = PagingConfig(30),
        pagingSourceFactory = {
            MyPagingSource(api)
        }
    )
        .flow
        .cachedIn(viewModelScope)
}