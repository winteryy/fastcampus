package winterry.part3.chapter5.repository

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import winterry.part3.chapter5.SearchService
import winterry.part3.chapter5.model.ListItem

class SearchRepositoryImpl(private val searchService: SearchService): SearchRepository {

    override fun search(query: String): Observable<List<ListItem>> {
        return searchService.searchImage(query)
            .zipWith(searchService.searchVideo(query)) { imageResult, videoResult ->
                mutableListOf<ListItem>().apply {
                    addAll(imageResult.documents)
                    addAll(videoResult.documents)
                }.sortedBy { it.dateTime }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}