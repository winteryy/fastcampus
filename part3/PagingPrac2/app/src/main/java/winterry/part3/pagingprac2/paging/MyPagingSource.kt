package winterry.part3.pagingprac2.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import winterry.part3.pagingprac2.data.GithubResponseItem
import winterry.part3.pagingprac2.network.GitApi

private const val STARTING_KEY = 1

class MyPagingSource(
    private val githubService: GitApi
) : PagingSource<Int, GithubResponseItem>() {

    init {
        Log.d("MyPagingSource", "Init")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubResponseItem> {

        val page = params.key ?: STARTING_KEY

        val response = githubService.getData(page, params.loadSize)

        val data = response.body()

        if (page != 1) {
            delay(1000)
        }

        return if (data != null) {
            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = page + (params.loadSize / 30)
            )
        } else {
            LoadResult.Page(
                data = emptyList(),
                prevKey = null,
                nextKey = null
            )
        }

    }

    override fun getRefreshKey(state: PagingState<Int, GithubResponseItem>): Int? {
        return null
    }
}