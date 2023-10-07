package winterry.part3.pagingprac4.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("html_url") val url: String,
)