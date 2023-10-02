package winterry.part3.chapter5.list

import winterry.part3.chapter5.model.ListItem

interface ItemHandler {
    fun onClickFavorite(item: ListItem)
}