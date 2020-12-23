package com.example.bookmark.viewmodels

import android.util.Log
import android.view.View
import androidx.lifecycle.*
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

    fun ascRate(view: View){
        Log.e("ascRate ! ",
            "dddddd" )
//        bookMarkViewModel.ascRate.observe(owner = viewLifecycleOwner) { bookMark ->
//            bookMark.let { adapter.submitList(it) }
//        }
    }
//    fun descRate(view: View){
//        Log.e("descRate ! ",
//            "dddddd" )
//        bookMarkViewModel.descRate.observe(owner = viewLifecycleOwner) { bookMark ->
//            bookMark.let { adapter.submitList(it) }
//        }
//    }
//    fun ascTime(view: View){
//        bookMarkViewModel.ascTimeStamp.observe(owner = viewLifecycleOwner) { bookMark ->
//            bookMark.let { adapter.submitList(it) }
//        }
//    }
//    fun descTime(view: View){
//        bookMarkViewModel.descTimeStamp.observe(owner = viewLifecycleOwner) { bookMark ->
//            bookMark.let { adapter.submitList(it) }
//        }
//    }

}