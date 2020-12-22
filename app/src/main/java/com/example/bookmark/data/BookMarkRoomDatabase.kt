package com.example.bookmark.data

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(BookMark::class), version = 1, exportSchema = false)
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
            bookMarkDao.deleteAll()

            bookMarkDao.insert(bookMark = BookMark(4001,
                     "여기어때 해남","12.0",
            "https://gccompany.co.kr/App/thumbnail/thumb_img_41.jpg", "https://gccompany.co.kr/App/image/img_41.jpg",
                    "모텔, 호텔, 리조트, 펜션, 캠핑, 글램핑, 게스트하우스, 액티비티, 방탈출, VR, 워터파크, 아웃도어",
            30000,"121212"))
            bookMarkDao.insert(bookMark = BookMark(401,
                    "해남","9.2",
                    "https://gccompany.co.kr/App/thumbnail/thumb_img_41.jpg", "https://gccompany.co.kr/App/image/img_41.jpg",
                    "모텔, 호텔, 리조트, 펜션, 캠핑, 글램핑, 게스트하우스, 액티비티, 방탈출, VR, 워터파크, 아웃도어",
                    30000,"121212"))
            bookMarkDao.insert(bookMark = BookMark(41,
                    "해남","2.0",
                    "https://gccompany.co.kr/App/thumbnail/thumb_img_41.jpg", "https://gccompany.co.kr/App/image/img_41.jpg",
                    "모텔, 호텔, 리조트, 펜션, 캠핑, 글램핑, 게스트하우스, 액티비티, 방탈출, VR, 워터파크, 아웃도어",
                    30000,"121212"))


//            // Add sample words.
//            var word = Word("Hello")
//            wordDao.insert(word)
//            word = Word("World!")
//            wordDao.insert(word)
//
//            // TODO: Add your own words!
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