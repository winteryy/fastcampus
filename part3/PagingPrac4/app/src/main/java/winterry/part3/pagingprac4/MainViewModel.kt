package winterry.part3.pagingprac4

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import winterry.part3.pagingprac4.model.Item
import winterry.part3.pagingprac4.network.GitApi
import winterry.part3.pagingprac4.network.RetrofitInstance

class MainViewModel(private val str: String): ViewModel() {

    private val api = RetrofitInstance.getInstance().create(GitApi::class.java)

    val items: Flow<PagingData<Item>> = Pager(
        config = PagingConfig(30),
        pagingSourceFactory = {
            GithubPagingSource(api, str)
        }
    )
        .flow
        .cachedIn(viewModelScope)
}