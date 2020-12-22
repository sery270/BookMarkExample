package com.example.bookmark.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookMarkDAO {
    // 평점 기준 오름차순 정렬
    @Query("SELECT * FROM BookMarkTable ORDER BY rate ASC")
    fun ascRate(): Flow<List<BookMark>>

    // 평점 기준 내림차순 정렬
    @Query("SELECT * FROM BookMarkTable ORDER BY rate DESC")
    fun descRate(): Flow<List<BookMark>>

    // 등록시간 기준 오름차순 정렬
    @Query("SELECT * FROM BookMarkTable ORDER BY TimeStamp ASC")
    fun ascTimeStamp(): Flow<List<BookMark>>

    // 등록시간 기준 내림차순 정렬
    @Query("SELECT * FROM BookMarkTable ORDER BY TimeStamp DESC")
    fun descTimeStamp(): Flow<List<BookMark>>

    // 즐겨 찾기한 상품인지 확인
    @Query("SELECT * FROM BookMarkTable WHERE id = (:id)")
    fun isBookMarked(id: Int): Boolean

    // 즐겨 찾기한 상품을 삽입
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(bookMark: BookMark)

    // 즐겨 찾기 해제한 상품을 삭제
    @Query("DELETE FROM BookMarkTable")
    suspend fun deleteAll()

}