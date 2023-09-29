package winterry.part3.chapter5.repository

import io.reactivex.rxjava3.core.Observable
import winterry.part3.chapter5.model.ListItem

interface SearchRepository {

    fun search(query: String): Observable<List<ListItem>>
}