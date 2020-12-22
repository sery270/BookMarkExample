package com.example.bookmark.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmark.R
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.bookmark.api.Product
import com.example.bookmark.data.BookMark

class BookMarkedListAdapter : ListAdapter<BookMark, BookMarkedListAdapter.BookMarkViewHolder>(BookMarksComparator()) {

    class BookMarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val id = itemView.findViewById<TextView>(R.id.item_book_marked_list_tv_id)
        private val thumbnail = itemView.findViewById<ImageView>(R.id.item_book_marked_list_iv)
        private val name = itemView.findViewById<TextView>(R.id.item_book_marked_list_tv_name)
        private val rate = itemView.findViewById<TextView>(R.id.item_book_marked_list_tv_rate)
        private val isBookMarked = itemView.findViewById<CheckBox>(R.id.item_book_marked_list_btn_book_mark)
        private val msg = itemView.findViewById<TextView>(R.id.item_book_marked_list_tv_msg)

        fun bind(bookMark: BookMark) {
            // id
            id.text = bookMark.id.toString()
            // thumbnail
            Glide.with(itemView).load(bookMark.thumbnail).into(thumbnail)
            // name
            name.text = bookMark.name
            // rate
            rate.text = bookMark.rate.toString()
            // bookMark
            isBookMarked.isChecked = true
            // msg
            msg.text = "201214 2324에 찜 하셨어요!"


        }

        companion object {
            fun create(parent: ViewGroup): BookMarkViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_book_marked_list, parent, false)
                return BookMarkViewHolder(view)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookMarkViewHolder {
        return BookMarkViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: BookMarkViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
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