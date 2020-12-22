package com.example.bookmark.data

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper

@Database(entities = arrayOf(BookMark::class), version = 1, exportSchema = false)
public abstract class BookMarkRoomDatabase : RoomDatabase() {

    abstract fun bookMarkDao(): BookMarkDAO

    companion object {
        // Singleton
        @Volatile
        private var INSTANCE: BookMarkRoomDatabase? = null

        fun getDatabase(context: Context): BookMarkRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookMarkRoomDatabase::class.java,
                    "BookMarkTable"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}