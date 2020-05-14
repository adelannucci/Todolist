package com.example.todoproject.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ItemAddViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemAddViewModel::class.java)) {
            return ItemAddViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}