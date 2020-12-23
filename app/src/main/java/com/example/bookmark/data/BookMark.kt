package com.example.bookmark.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Each @Entity class represents a SQLite table
@Entity(tableName = "BookMarkTable")
class BookMark(
    // api에서 얻어오는 값
        @PrimaryKey @ColumnInfo(name = "id") val id: Int,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "rate") val rate: Double,
        @ColumnInfo(name = "thumbnail") val thumbnail: String,
        @ColumnInfo(name = "imagePath") val imagePath: String,
        @ColumnInfo(name = "subject") val subject: String,
        @ColumnInfo(name = "price") val price: Int,

    // 클라에서 생성할 값
        @ColumnInfo(name = "timeStamp") val timeStamp: String
    )