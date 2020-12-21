package com.example.bookmark.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.bookmark.R
import com.example.bookmark.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class ViewPagerFragment : Fragment() {

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private val tabLayoutTextArray = arrayOf("전체 리스트","즐겨찾기 리스트")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager = view.findViewById(R.id.view_pager_fg_vp)
        viewPager.adapter = viewPagerAdapter

        tabLayout = view.findViewById(R.id.view_pager_fg_tl)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabLayoutTextArray[position]
        }.attach()

    }

}