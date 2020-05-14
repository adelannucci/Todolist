package com.example.todoproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.sql.Date

internal object Converters {
    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    @JvmStatic
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}

@Entity(tableName = "item_database")
class Item(val title: String, val description: String) {
    @PrimaryKey(autoGenerate = true)
    var itemId = 0
    var done: Boolean = false
    var createdDate: Date? = null
}