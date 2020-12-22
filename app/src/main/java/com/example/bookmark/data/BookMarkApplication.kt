package com.example.bookmark.data

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class BookMarkApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { BookMarkRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { BookMarkRepository(database.bookMarkDao()) }
}