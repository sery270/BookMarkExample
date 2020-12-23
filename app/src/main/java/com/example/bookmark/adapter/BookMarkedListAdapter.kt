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
import kotlin.coroutines.coroutineContext

class BookMarkedListAdapter :
    ListAdapter<BookMark, BookMarkedListAdapter.BookMarkViewHolder>(BookMarksComparator()) {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookMarkViewHolder {
//        return BookMarkViewHolder(ItemBookMarkedListBinding.inflate(LayoutInflater.from(parent.context),parent))
//    }
//
//    override fun onBindViewHolder(holder: BookMarkViewHolder, position: Int) {
//        val bookMark = getItem(position)
//        (holder as BookMarkViewHolder).bind(bookMark)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookMarkViewHolder {
        return BookMarkViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: BookMarkViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class BookMarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val id = itemView.findViewById<TextView>(R.id.item_book_marked_list_tv_id)
        private val thumbnail = itemView.findViewById<ImageView>(R.id.item_book_marked_list_iv)
        private val name = itemView.findViewById<TextView>(R.id.item_book_marked_list_tv_name)
        private val rate = itemView.findViewById<TextView>(R.id.item_book_marked_list_tv_rate)
        private val isBookMarked =
            itemView.findViewById<CheckBox>(R.id.item_book_marked_list_btn_book_mark)
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
            GlobalScope.launch {
                isBookMarked.isChecked = (itemView.context.applicationContext as BookMarkApplication).repository.isBookMarked(bookMark.id)
            }
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

//    class BookMarkViewHolder(
//        private val binding: ItemBookMarkedListBinding
//    ) : RecyclerView.ViewHolder(binding.root) {
//        init {
//            binding.setClickListener {
//                binding.bookMark?.let { bookMark ->
//                    navigateToDetail(bookMark, it)
//                }
//            }
//        }
//
//        private fun navigateToDetail(
//            bookMark: BookMark,
//            view: View
//        ) {
//
//            val direction = R.id.action_view_pager_fragment_to_detail_fragment
////            val direction = ViewPagerFragmentD
//            view.findNavController().navigate(direction)
//        }
//
//        fun bind(item: BookMark) {
//                        // thumbnail
//            Glide.with(itemView).load(item.thumbnail).into(itemView.findViewById<ImageView>(R.id.item_book_marked_list_iv))
//            binding.apply {
//                bookMark = item
//                executePendingBindings()
//            }
//        }
//
//
//
//    }
    }

    private class BookMarksComparator : DiffUtil.ItemCallback<BookMark>() {
        override fun areItemsTheSame(oldItem: BookMark, newItem: BookMark): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: BookMark, newItem: BookMark): Boolean {
            return oldItem.id == newItem.id
        }
    }
}


//@BindingAdapter("imageFromUrl")
//fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
//    if (!imageUrl.isNullOrEmpty()) {
//        Glide.with(view.context)
//            .load(imageUrl)
//            .into(view)
//    }
//}