package com.example.bookmark.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bookmark.fragment.BookMarkedListFragment
import com.example.bookmark.fragment.ListFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        return when(position){
            0 -> ListFragment()
            1 -> BookMarkedListFragment()
            else -> ListFragment()
        }
    }

}