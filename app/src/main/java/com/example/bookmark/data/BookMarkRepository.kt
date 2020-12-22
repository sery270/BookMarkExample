package com.example.bookmark.data

import androidx.annotation.WorkerThread

class BookMarkRepository(private val bookMarkDao: BookMarkDAO) {


//    // 평점 기준 오름차순 정렬
//    fun ascRate() = bookMarkDao.ascRate()
//
//    // 평점 기준 내림차순 정렬
//    fun descRate() = bookMarkDao.descRate()
//
//    // 등록시간 기준 오름차순 정렬
//    fun ascTimeStamp() = bookMarkDao.ascTimeStamp()
//
//    // 등록시간 기준 내림차순 정렬
//    fun descTimeStamp() = bookMarkDao.descTimeStamp()

    // 평점 기준 오름차순 정렬
    val ascRate = bookMarkDao.ascRate()

    // 평점 기준 내림차순 정렬
    val descRate = bookMarkDao.descRate()

    // 등록시간 기준 오름차순 정렬
    val ascTimeStamp = bookMarkDao.ascTimeStamp()

    // 등록시간 기준 내림차순 정렬
    val descTimeStamp = bookMarkDao.descTimeStamp()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(bookMark: BookMark) {
        bookMarkDao.insert(bookMark)
    }

    suspend fun isBookMarked(id: Int): Boolean {
        return bookMarkDao.isBookMarked(id) != null
    }




}