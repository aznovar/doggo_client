package com.ru.appdoggo.ui.friends

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ru.appdoggo.ui.core.BaseFragment

class FriendsFragmentSectionPagerAdapter(fragment: BaseFragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2


    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int)
        val fragment = FriendsListFragment()
        fragment.arguments = Bundle().apply {
            putInt("ARG_OBJECT", position + 1)
        }
        return fragment
    }



}