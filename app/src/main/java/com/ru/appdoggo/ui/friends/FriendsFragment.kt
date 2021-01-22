package com.ru.appdoggo.ui.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ru.appdoggo.R
import com.ru.appdoggo.ui.core.BaseFragment

class FriendsFragment : BaseFragment() {

    override val layoutId = R.layout.fragment_friends
    override val titleToolbar = R.string.title_friends

    private lateinit var fragmentSectionPagerAdapter: FriendsFragmentSectionPagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentSectionPagerAdapter = FriendsFragmentSectionPagerAdapter(this)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = fragmentSectionPagerAdapter
        tabLayout = view.findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "OBJECT ${(position + 1)}"
        }.attach()
    }

}