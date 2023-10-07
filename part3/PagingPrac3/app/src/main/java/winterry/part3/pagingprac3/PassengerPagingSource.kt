package winterry.part3.pagingprac3

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import winterry.part3.pagingprac3.data.Data
import winterry.part3.pagingprac3.network.PassengerApi
import winterry.part3.pagingprac3.new_data.NewItem
import winterry.part3.pagingprac3.new_network.GithubApi

private const val STARTING_KEY = 1
private const val QUERY_STRING = "android"

class PassengerPagingSource(
    private val githubApi: GithubApi
): PagingSource<Int, NewItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewItem> {
        val page = params.key ?: STARTING_KEY

        val response = githubApi.getData(QUERY_STRING, page, params.loadSize)

        val data = response.body()?.items

        if(page != STARTING_KEY) {
            delay(1000)
        }

        return if(data == null) {
            LoadResult.Page(
                data = emptyList(),
                prevKey = null,
                nextKey = null
            )
        } else {
            LoadResult.Page(
                data = data,
                prevKey = if(page==1) null else page-1,
                nextKey = page + params.loadSize/30
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NewItem>): Int? {

        val anchorPosition = state.anchorPosition

        return anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}