package com.example.bookmark.api

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <ResponseType> Call<ResponseType>.customEnqueue(
    onFail: () -> Unit = { Log.d("network", "통신에 실패했습니다.") },
    onSuccess: (ResponseType) -> Unit,
    onError: () -> Unit
) {
    this.enqueue(object : Callback<ResponseType> {
        override fun onFailure(call: Call<ResponseType>, t: Throwable) {
            onFail()
        }

        override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
            // body 가 존재한다면, statusCode가 200-300
            // let 함수를 통해 response 객체를 it 으로 사용
            response.body()?.let {
                onSuccess(it) // 통신 결과를 전달

            } ?: onError() // statusCode가 200-300 사이가 아니라면, onError 호출
        }

    })
}