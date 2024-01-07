package com.winterry.chapter8.data.source.local

import androidx.room.TypeConverter
import com.winterry.chapter8.util.DateUtil
import java.util.Date

class DateConverter {

    @TypeConverter
    fun toDate(timeStamp: String?): Date? {
        return timeStamp?.let { DateUtil.dbDateFormat.parse(it) }
    }

    @TypeConverter
    fun toTimeStamp(date: Date?): String? {
        return DateUtil.dbDateFormat.format(date)
    }
}