package winterry.part1.chapter7_review

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "wordTable")
data class Word(
    val text : String,
    val mean : String,
    val type : String,
    @PrimaryKey(autoGenerate = true) val id : Int = 0
) : Parcelable
