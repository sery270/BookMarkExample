package com.example.bookmark.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// singleton 사용
object RequestToServer {
    var retrofit = Retrofit.Builder()
        .baseUrl("https://www.gccompany.co.kr")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: RequestInterface = retrofit.create<RequestInterface>(RequestInterface::class.java)
}