package winterry.part3.chapter6.remote

import android.view.View
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import winterry.part3.chapter6.model.Coupon
import winterry.part3.chapter6.model.Empty
import winterry.part3.chapter6.model.FullAd
import winterry.part3.chapter6.model.Horizontal
import winterry.part3.chapter6.model.Image
import winterry.part3.chapter6.model.ListItem
import winterry.part3.chapter6.model.Sale
import winterry.part3.chapter6.model.SellItem
import winterry.part3.chapter6.model.ViewPager
import winterry.part3.chapter6.model.ViewType
import java.lang.reflect.Type

class ListItemDeserializer: JsonDeserializer<ListItem> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): ListItem {

        val viewTypeName = json.asJsonObject.getAsJsonPrimitive("viewType").asString
        val gson = GsonBuilder()
            .registerTypeAdapter(ListItem::class.java, ListItemDeserializer())
            .create()

        return try {
            when(viewTypeName) {
                ViewType.VIEW_PAGER.name -> gson.fromJson(json, ViewPager::class.java)
                ViewType.HORIZONTAL.name -> gson.fromJson(json, Horizontal::class.java)
                ViewType.FULL_AD.name -> gson.fromJson(json, FullAd::class.java)

                ViewType.SELL_ITEM.name -> gson.fromJson(json, SellItem::class.java)
                ViewType.SALE.name -> gson.fromJson(json, Sale::class.java)
                ViewType.IMAGE.name -> gson.fromJson(json, Image::class.java)
                ViewType.COUPON.name -> gson.fromJson(json, Coupon::class.java)

                else -> gson.fromJson(json, Empty::class.java)
            }
        } catch (e: Exception) {
            gson.fromJson(json, Empty::class.java)
        }
    }

}