package com.example.bookmark.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookmark.data.BookMarkRepository

class BookMarkViewModelFactory(private val repository: BookMarkRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookMarkViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookMarkViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }

}