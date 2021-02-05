package com.ru.appdoggo.ui.friends

import android.os.Bundle
import android.view.View
import com.ru.appdoggo.App
import com.ru.appdoggo.R
import com.ru.appdoggo.domain.entities.friends.FriendsEntity
import com.ru.appdoggo.domain.type.None
import com.ru.appdoggo.presentation.viewmodel.FriendsViewModel
import com.ru.appdoggo.ui.core.BaseListFragment
import com.ru.appdoggo.ui.core.ext.onFailure
import com.ru.appdoggo.ui.core.ext.onSuccess
import com.ru.appdoggo.ui.friends.adapters.FriendsRequestsAdapter

class FriendsRequestsFragment: BaseListFragment() {

    override val viewAdapter = FriendsRequestsAdapter()
    override val layoutId = R.layout.inner_fragment_list

    private lateinit var friendsViewModel: FriendsViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
            friendsViewModel = viewModel {
                onSuccess(getFriendshipRequestData, ::handleFriendRequests)
                onSuccess(approveFriendshipRequestData, ::handleFriendRequestAction)
                //todo  onSuccess(cancelFriendData, ::handleFriendRequestAction)
                onFailure(failureData, ::handleFailure)
            }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnItemClickListener { item, v ->
            (item as? FriendsEntity)?.let {
                when (v.id) {
                    R.id.btnApprove -> {
                        friendsViewModel.approveFriendship(it)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        friendsViewModel.getFriendshipRequests()
    }

    private fun handleFriendRequests(requests: List<FriendsEntity>?) {
        if (requests != null) {
            viewAdapter.clear()
            viewAdapter.add(requests)
            viewAdapter.notifyDataSetChanged()
        }
    }

    private fun handleFriendRequestAction(none: None?) {
        friendsViewModel.getFriendshipRequests()
    }
}