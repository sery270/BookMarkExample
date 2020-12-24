package com.example.bookmark.data

import androidx.annotation.WorkerThread

class BookMarkRepository(private val bookMarkDao: BookMarkDAO) {

    // 평점 기준 오름차순 정렬 데이터
    val ascRate = bookMarkDao.ascRate()

    // 평점 기준 내림차순 정렬 데이터
    val descRate = bookMarkDao.descRate()

    // 등록시간 기준 오름차순 정렬 데이터
    val ascTimeStamp = bookMarkDao.ascTimeStamp()

    // 등록시간 기준 내림차순 정렬 데이터
    val descTimeStamp = bookMarkDao.descTimeStamp()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(bookMark: BookMark) {
        bookMarkDao.insert(bookMark)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll(id: Int) {
        bookMarkDao.deleteAll(id)
    }

    fun isBookMarked(id: Int): Boolean {
        return bookMarkDao.isBookMarked(id) != null
    }


}