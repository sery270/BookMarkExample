package com.example.bookmark.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bookmark.fragment.BookMarkedListFragment
import com.example.bookmark.fragment.DefaultListFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    // 반환 값 == page 개수
    override fun getItemCount(): Int = 2

    // 해당 함수의 매개변수는 현태 선택 된 탭의 인덱스
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DefaultListFragment()
            1 -> BookMarkedListFragment()
            else -> DefaultListFragment()
        }
    }

}