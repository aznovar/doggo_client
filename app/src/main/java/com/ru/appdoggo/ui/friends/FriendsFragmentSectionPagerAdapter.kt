package com.ru.appdoggo.ui.friends

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ru.appdoggo.ui.core.BaseFragment

class FriendsFragmentSectionPagerAdapter(fragment: BaseFragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                return FriendsListFragment()
            }
            1 -> {
                return FriendsRequestsFragment()
            }
            else -> return FriendsRequestsFragment()
        }
    }

}