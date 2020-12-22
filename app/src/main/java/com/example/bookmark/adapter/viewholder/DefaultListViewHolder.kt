package com.example.bookmark.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookmark.R
import com.example.bookmark.api.Product

class DefaultListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
    private val thumbnail = itemView.findViewById<ImageView>(R.id.item_list_iv)
    private val name = itemView.findViewById<TextView>(R.id.item_list_tv_name)
    private val rate = itemView.findViewById<TextView>(R.id.item_list_tv_rate)
    private val bookMark = itemView.findViewById<ImageView>(R.id.item_list_btn_book_mark)

//    val id: Int,
//    val name: String,
//    val thumbnail: String,
//    val rate: Float,
//    val description: MutableList<Description>

    fun bind(product: Product){
        // thumbnail
        Glide.with(itemView).load(product.thumbnail).into(thumbnail)


    }
}