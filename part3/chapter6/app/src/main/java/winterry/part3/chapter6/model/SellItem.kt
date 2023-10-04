package winterry.part3.chapter6.model

data class SellItem(
    val imageUrl: String,
    val name: String,
    val price: Long,
    val badge: String? = null,
    val rating: Float? = null
): ListItem {
    override val viewType: ViewType
        get() = ViewType.SELL_ITEM
}
