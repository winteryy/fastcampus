package winterry.part2.chapter3

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("message")
    val b: String
)