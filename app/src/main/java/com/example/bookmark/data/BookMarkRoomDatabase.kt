package com.example.bookmark.data

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [BookMark::class], version = 1, exportSchema = false)
public abstract class BookMarkRoomDatabase : RoomDatabase() {

    abstract fun bookMarkDao(): BookMarkDAO

    private class BookMarkDatabaseCallback(
            private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.bookMarkDao())
                }
            }
        }

        suspend fun populateDatabase(bookMarkDao: BookMarkDAO) {
            // Delete all content here.
//            bookMarkDao.deleteAll()
        }
    }

    companion object {
        // Singleton
        @Volatile
        private var INSTANCE: BookMarkRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): BookMarkRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        BookMarkRoomDatabase::class.java,
                        "BookMarkTable"
                )
                        .addCallback(BookMarkDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}