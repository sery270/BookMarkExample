package com.example.bookmark.viewmodels

import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.example.bookmark.api.Description
import com.example.bookmark.api.Product
import com.example.bookmark.data.BookMark
import com.example.bookmark.data.BookMarkRepository
import kotlinx.coroutines.launch

class BookMarkViewModel(private val repository: BookMarkRepository) : ViewModel() {

    // 평점 기준 오름차순 정렬
    val ascRate = repository.ascRate.asLiveData()

    // 평점 기준 내림차순 정렬
    val descRate = repository.descRate.asLiveData()

    // 등록시간 기준 오름차순 정렬
    val ascTimeStamp = repository.ascTimeStamp.asLiveData()

    // 등록시간 기준 내림차순 정렬
    val descTimeStamp = repository.descTimeStamp.asLiveData()

    fun insert(bookMark: BookMark) = viewModelScope.launch {
        repository.insert(bookMark)
    }

    var product = Product(41,
                    "해남",2.0,
                    "https://gccompany.co.kr/App/thumbnail/thumb_img_41.jpg", description = Description("https://gccompany.co.kr/App/image/img_41.jpg",
                    "모텔, 호텔, 리조트, 펜션, 캠핑, 글램핑, 게스트하우스, 액티비티, 방탈출, VR, 워터파크, 아웃도어",
                    30000)
    )


}