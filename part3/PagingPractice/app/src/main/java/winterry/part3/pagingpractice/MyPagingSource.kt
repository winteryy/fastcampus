package winterry.part3.pagingpractice

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay

private const val STARTING_KEY = 1

class MyPagingSource: PagingSource<Int, User>() {

    init {
        Log.d("MyPagingSource", "PagingSource Init")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        Log.d("MyPagingSource", "Load")

        val page = params.key ?: STARTING_KEY

        val range = page.until(page+params.loadSize)

        if(page != STARTING_KEY) {
            delay(1000)
        }

        return LoadResult.Page(
            data = range.map { number ->
                User(
                    id = number,
                    userName = "UserNumber is $number"
                )
            },
            prevKey = null,
            nextKey = range.last + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return null
    }

}