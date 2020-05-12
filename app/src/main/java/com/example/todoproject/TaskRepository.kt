package com.example.todoproject

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class TaskRepository(private val itemDao: ItemDao) {

    val list: LiveData<List<Item>> = itemDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(item: Item) {
        itemDao.insert(item)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(item: Item) {
        itemDao.update(item)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(item: Item) {
        itemDao.delete(item)
    }
}