package com.example.bookmark.data

class BookMarkRepository(private val bookMarkDao: BookMarkDAO) {


    // 평점 기준 오름차순 정렬
    fun ascRate() = bookMarkDao.ascRate()

    // 평점 기준 내림차순 정렬
    fun descRate() = bookMarkDao.descRate()

    // 등록시간 기준 오름차순 정렬
    fun ascTimeStamp() = bookMarkDao.ascTimeStamp()

    // 등록시간 기준 내림차순 정렬
    fun descTimeStamp() = bookMarkDao.descTimeStamp()


}