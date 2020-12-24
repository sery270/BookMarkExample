package com.example.bookmark.adapter

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmark.R
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.bookmark.api.Product
import com.example.bookmark.data.BookMark
import com.example.bookmark.data.BookMarkApplication
import com.example.bookmark.data.BookMarkDAO
import com.example.bookmark.data.BookMarkRepository
import com.example.bookmark.data.BookMarkRoomDatabase.Companion.getDatabase
import com.example.bookmark.databinding.ItemBookMarkedListBinding
import com.example.bookmark.fragment.ViewPagerFragment
import com.example.bookmark.viewmodels.BookMarkViewModel
import com.example.bookmark.viewmodels.BookMarkViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.coroutineContext

class BookMarkedListAdapter :
    ListAdapter<BookMark, BookMarkedListAdapter.BookMarkViewHolder>(BookMarksComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookMarkViewHolder {
        return BookMarkViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: BookMarkViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)

        // 각 아이템에 onClickListener를 달고, 그 안에 직접 만든 itemClickListener 연결.
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it,current)
            holder.bind(current)
        }

        // 각 아이템의 하트 토글에 onClickListener를 달고, 그 안에 직접 만든 bookMarkClickListener 연결.
        holder.itemView.findViewById<CheckBox>(R.id.item_book_marked_list_btn_book_mark)
            .setOnClickListener {
                bookMarkClickListener.onClick(it, current)
                holder.bind(current)
            }
    }

    class BookMarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val thumbnail = itemView.findViewById<ImageView>(R.id.item_book_marked_list_iv)
        private val name = itemView.findViewById<TextView>(R.id.item_book_marked_list_tv_name)
        private val rate = itemView.findViewById<TextView>(R.id.item_book_marked_list_tv_rate)
        private val isBookMarked =
            itemView.findViewById<CheckBox>(R.id.item_book_marked_list_btn_book_mark)
        private val msg = itemView.findViewById<TextView>(R.id.item_book_marked_list_tv_msg)

        fun bind(bookMark: BookMark) {
            // thumbnail
            Glide.with(itemView).load(bookMark.thumbnail).into(thumbnail)
            // name
            name.text = bookMark.name
            // rate
            rate.text = bookMark.rate.toString()
            // bookMark
            GlobalScope.launch {
                isBookMarked.isChecked =
                    (itemView.context.applicationContext as BookMarkApplication).repository.isBookMarked(
                        bookMark.id
                    )
            }
            // msg
            msg.text =
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date(bookMark.timeStamp)) + "에 찜 하셨어요!"


        }

        companion object {
            fun create(parent: ViewGroup): BookMarkViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_book_marked_list, parent, false)
                return BookMarkViewHolder(view)
            }
        }
    }

    private class BookMarksComparator : DiffUtil.ItemCallback<BookMark>() {
        override fun areItemsTheSame(oldItem: BookMark, newItem: BookMark): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: BookMark, newItem: BookMark): Boolean {
            return oldItem.id == newItem.id
        }
    }


    interface ItemClickListener {
        fun onClick(view: View, bookMark: BookMark)
    }

    private lateinit var bookMarkClickListener: ItemClickListener
    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    fun setBookMarkClickListener(bookMarkClickListener: ItemClickListener) {
        this.bookMarkClickListener = bookMarkClickListener


    }
}
