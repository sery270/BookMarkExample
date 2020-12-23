package com.example.bookmark.api

data class ResponseProductInfo(
    val msg: String,
    val code: Int,
    val data: Data
)

data class Data(
    val totalCount: Int,
    val product: MutableList<Product>
)

data class Product(
    val id: Int,
    val name: String,
    val rate: Double,
    val thumbnail: String,
    val description: Description
)

data class Description(
    val imagePath: String,
    val subject: String,
    val price: Int
)