package com.example.todoproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.todoproject.model.ItemDatabase
import com.example.todoproject.model.TaskRepository
import com.example.todoproject.model.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ItemAddViewModel(application: Application) : AndroidViewModel(application) {
    private var viewModelJob = Job()
    private val coroutineContext: CoroutineContext
        get() = viewModelJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)
    val list: LiveData<List<Item>>
    private val repository: TaskRepository

    init {
        val itemDao = ItemDatabase.getInstance(
            application,
            scope
        ).itemDao()
        repository = TaskRepository(itemDao)
        list = repository.list
    }

    fun insert(item: Item) = scope.launch(Dispatchers.IO) {
        repository.insert(item)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}