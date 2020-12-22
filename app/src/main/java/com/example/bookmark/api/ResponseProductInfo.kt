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
    val thumbnail: String,
    val rate: Float,
    val description: Description
)

data class Description(
    val price: Int,
    val subject: String,
    val imagePath: String

)