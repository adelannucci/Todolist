package com.example.todoproject

import androidx.lifecycle.LiveData
import androidx.room.*

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