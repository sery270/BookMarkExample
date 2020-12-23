package com.example.bookmark.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookmark.R
import com.example.bookmark.api.Product
import com.example.bookmark.data.BookMark
import com.example.bookmark.data.BookMarkApplication
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DefaultListAdapter(private val context: Context) : RecyclerView.Adapter<DefaultListAdapter.DefaultViewHolder>() {
    var datas = mutableListOf<Product>()
    private lateinit var bookMark: BookMark

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultListAdapter.DefaultViewHolder {
        return DefaultListAdapter.DefaultViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DefaultListAdapter.DefaultViewHolder, position: Int) {
        holder.bind(datas[position])
        holder.setIsRecyclable(false)


        //view에 onClickListener를 달고, 그 안에서 직접 만든 itemClickListener를 연결시킨다
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it,position,datas[position], datas)
            holder.bind(datas[position])
        }

        //view에 onClickListener를 달고, 그 안에서 직접 만든 itemClickListener를 연결시킨다
        holder.itemView.findViewById<CheckBox>(R.id.item_list_btn_book_mark).setOnClickListener {
            bookMarkClickListener.onClick(it,position,datas[position], datas)
            holder.bind(datas[position])
        }
    }


    override fun getItemCount(): Int {
        return datas.size
    }

    class DefaultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val id = itemView.findViewById<TextView>(R.id.item_list_tv_id)
        private val thumbnail = itemView.findViewById<ImageView>(R.id.item_list_iv)
        private val name = itemView.findViewById<TextView>(R.id.item_list_tv_name)
        private val rate = itemView.findViewById<TextView>(R.id.item_list_tv_rate)
        private val isBookMarked = itemView.findViewById<CheckBox>(R.id.item_list_btn_book_mark)


        fun bind(product: Product) {
            // id
            id.text = product.id.toString()
            // thumbnail
            Glide.with(itemView).load(product.thumbnail).into(thumbnail)
            // name
            name.text = product.name
            // rate
            rate.text = product.rate.toString()
            // bookMark
            GlobalScope.launch {
                isBookMarked.isChecked = (itemView.context.applicationContext as BookMarkApplication).repository.isBookMarked(product.id)
            }

        }

        companion object {
            fun create(parent: ViewGroup): DefaultListAdapter.DefaultViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list, parent, false)
                return DefaultListAdapter.DefaultViewHolder(view)
            }
        }
    }

    //클릭 인터페이스 정의
    interface ItemClickListener{
        fun onClick(view: View, position: Int, data: Product, datas: MutableList<Product>)
    }

    //클릭리스너 선언
    private lateinit var itemClickListener: ItemClickListener
    private lateinit var bookMarkClickListener: ItemClickListener

    //클릭리스너 등록 메소드
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    fun setBookMarkClickListener(bookMarkClickListener: ItemClickListener) {
        this.bookMarkClickListener = bookMarkClickListener


    }

}

