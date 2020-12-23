package com.example.bookmark.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.bookmark.R
import com.example.bookmark.adapter.DefaultListAdapter
import com.example.bookmark.api.Description
import com.example.bookmark.api.Product
import com.example.bookmark.data.BookMark
import com.example.bookmark.data.BookMarkApplication
import com.example.bookmark.fragment.DefaultListFragment.Companion.product
import com.example.bookmark.viewmodels.BookMarkViewModel
import com.example.bookmark.viewmodels.BookMarkViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DetailFragment : Fragment() {
    private val bookMarkViewModel: BookMarkViewModel by viewModels {
        BookMarkViewModelFactory((activity?.application as BookMarkApplication).repository)
    }
    lateinit var adapter: DefaultListAdapter
    private lateinit var bookMark: BookMark
//
//    val product: Product by lazy {
//        bookMarkViewModel.product
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imagePath = view.findViewById<ImageView>(R.id.detail_fg_iv)
        val name = view.findViewById<TextView>(R.id.detail_fg_tv_name)
        val rate = view.findViewById<TextView>(R.id.detail_fg_tv_rate)
        val subject = view.findViewById<TextView>(R.id.detail_fg_tv_subject)
        val price = view.findViewById<TextView>(R.id.detail_fg_tv_price)
        val isBookMarked = view.findViewById<CheckBox>(R.id.detail_fg_btn_book_mark)

        // imagePath
        Glide.with(view).load(product.description.imagePath).into(imagePath)
        // name
        name.text = product.name
        // rate
        rate.text = product.rate.toString()
        // subject
        subject.text = product.description.subject
        // price
        price.text = product.description.price.toString()
        // bookMark
        GlobalScope.launch {
            isBookMarked.isChecked = (view.context.applicationContext as BookMarkApplication).repository.isBookMarked(product.id)
        }

        // 아이템의 하트를 누르면 즐겨찾기 삽입
        isBookMarked.setOnClickListener {
            if (!isBookMarked.isChecked) {
                GlobalScope.launch {
                    bookMarkViewModel.deleteAll(product.id)
                    // default list는 뷰 모델 아니므로, 적어줘야함
                    isBookMarked.isChecked = (view.context.applicationContext as BookMarkApplication).repository.isBookMarked(
                        product.id)
                }

            } else {
                bookMark = BookMark(
                    product.id,
                    product.name,
                    product.rate,
                    product.thumbnail,
                    product.description.imagePath,
                    product.description.subject,
                    product.description.price,
                    System.currentTimeMillis()
                )
                bookMarkViewModel.insert(bookMark)
            }
        }




        // 뒤로가기
        // navigateUp() 현재 스택을 팝
        view.findViewById<ImageView>(R.id.detail_fg_btn_back).setOnClickListener {
            it.findNavController().navigateUp()
        }
    }


}