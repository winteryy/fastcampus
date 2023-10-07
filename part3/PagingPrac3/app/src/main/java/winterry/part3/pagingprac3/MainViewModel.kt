package winterry.part3.pagingprac3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import winterry.part3.pagingprac3.data.Data
import winterry.part3.pagingprac3.network.PassengerApi
import winterry.part3.pagingprac3.network.RetrofitInstance
import winterry.part3.pagingprac3.new_data.NewItem
import winterry.part3.pagingprac3.new_network.GithubApi
import winterry.part3.pagingprac3.new_network.NewRetrofitInstance

class MainViewModel: ViewModel() {

    private val api = NewRetrofitInstance.getInstance().create(GithubApi::class.java)

    val items: Flow<PagingData<NewItem>> = Pager(
        config = PagingConfig(30),
        pagingSourceFactory = {
            PassengerPagingSource(api)
        }
    )
        .flow
        .cachedIn(viewModelScope)
}