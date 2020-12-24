package com.example.bookmark.data

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class BookMarkApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { BookMarkRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { BookMarkRepository(database.bookMarkDao()) }
}