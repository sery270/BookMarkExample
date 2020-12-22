package com.example.bookmark.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmark.R
import com.example.bookmark.adapter.BookMarkedListAdapter
import com.example.bookmark.data.BookMarkApplication
import com.example.bookmark.util.ItemDecoration
import com.example.bookmark.viewmodels.BookMarkViewModel
import com.example.bookmark.viewmodels.BookMarkViewModelFactory


class BookMarkedListFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: BookMarkedListAdapter
    private val newBookMarkRequestCode = 1
    private val bookMarkViewModel: BookMarkViewModel by viewModels {
        BookMarkViewModelFactory((activity?.application as BookMarkApplication).repository)
        //context?.applicationContext  activity?.application과 의 차이점에 대해서 찾아보자
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_marked_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // recyclerView init
        recyclerView = view.findViewById<RecyclerView>(R.id.book_marked_list_fg_rv)
        adapter = BookMarkedListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.addItemDecoration(ItemDecoration())


        bookMarkViewModel.ascRate.observe(owner = viewLifecycleOwner) { bookMark ->
            bookMark.let { adapter.submitList(it) }
        }

//        bookMarkViewModel.insert(word)
    }

}