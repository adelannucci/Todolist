package com.example.todoproject.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todoproject.model.Item

@Dao
interface ItemDao {
    @Insert
    fun insert(item: Item)

    @Update
    fun update(item: Item)

    @Delete
    fun delete(item: Item)

    @Query("DELETE FROM item_database")
    fun deleteAll()

    @Query("SELECT * FROM item_database ORDER BY datetime(createdDate) DESC")
    fun getAll(): LiveData<List<Item>>
}