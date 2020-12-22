package com.example.bookmark.data

import android.app.Application

class BookMarkApplication : Application() {
    val database by lazy { BookMarkRoomDatabase.getDatabase(this) }
    val repository by lazy { BookMarkRepository(database.bookMarkDao()) }
}