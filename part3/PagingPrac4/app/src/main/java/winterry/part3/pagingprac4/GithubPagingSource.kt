package winterry.part3.pagingprac4

import androidx.paging.PagingSource
import androidx.paging.PagingState
import winterry.part3.pagingprac4.model.Item
import winterry.part3.pagingprac4.network.GitApi

private const val STARTING_KEY = 1

class GithubPagingSource(
    private val gitApi: GitApi,
    private val query: String,
): PagingSource<Int, Item>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val page = params.key ?: STARTING_KEY

        val response = gitApi.getData(query, page, params.loadSize)

        val data = response.items

        return LoadResult.Page(
            data = data,
            prevKey = if(page == 1) null else page-1,
            nextKey = page + params.loadSize/30
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        val anchorPosition = state.anchorPosition
        return anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

}