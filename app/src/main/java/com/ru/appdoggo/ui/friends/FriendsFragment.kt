package com.ru.appdoggo.ui.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ru.appdoggo.R
import com.ru.appdoggo.ui.core.BaseFragment

private val FRIENDS_TABS_TITLES = arrayOf(
    "Список друзей",
    "Заявки в друзья"
)

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
            tab.text = FRIENDS_TABS_TITLES[position]
        }.attach()
        addFabAddFriend(view)
    }

    private fun addFabAddFriend(view: View) {
        val fab: FloatingActionButton = view.findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val action = FriendsFragmentDirections.actionFriendsToAddFriend()
            view.findNavController().navigate(action)
        }
    }

    private fun addFragment():Fragment{
        return AddFriendFragment()
    }

}