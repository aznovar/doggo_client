package com.ru.appdoggo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ru.appdoggo.domain.entities.friends.FriendsEntity
import com.ru.appdoggo.domain.type.None
import com.ru.appdoggo.domain.usecases.friends.AddFriend
import com.ru.appdoggo.domain.usecases.friends.ApproveFriendshipRequest
import com.ru.appdoggo.domain.usecases.friends.GetFriendsList
import com.ru.appdoggo.domain.usecases.friends.GetListOfFriendshipRequests
import javax.inject.Inject

class FriendsViewModel @Inject constructor(
    val addFriendUseCase: AddFriend,
    val approveFriendshipRequestUseCase: ApproveFriendshipRequest,
    val getListOfFriendshipRequestsUseCase: GetListOfFriendshipRequests,
    val getFriendsListUseCase: GetFriendsList
) : BaseViewModel() {

    var addFriendData: MutableLiveData<None> = MutableLiveData()
    var approveFriendshipRequestData: MutableLiveData<None> = MutableLiveData()
    var getFriendshipRequestData : MutableLiveData<List<FriendsEntity>> = MutableLiveData()
    var getFriendsListData : MutableLiveData<List<FriendsEntity>> = MutableLiveData()

    fun getFriendsList(){
        getFriendsListUseCase(None()){it.either(::handleFailure, ::handleListOfFriendRequests)}
    }

    fun getFriendshipRequests(){
        getListOfFriendshipRequestsUseCase(None()){it.either(::handleFailure, ::handleFriendshipRequests)}
    }

    fun addFriend(email: String) {
        addFriendUseCase(AddFriend.Params(email)) { it.either(::handleFailure, ::handleAddFriend) }
    }

    fun approveFriendship(friendEntity: FriendsEntity) {
        approveFriendshipRequestUseCase(friendEntity) { it.either(::handleFailure, ::handleApproveFriend) }
    }

    private fun handleListOfFriendRequests(friends: List<FriendsEntity>){
        getFriendsListData.value = friends
    }

    private fun handleFriendshipRequests(friends: List<FriendsEntity>) {
        getFriendshipRequestData.value = friends
    }

    private fun handleAddFriend(none: None?) {
        addFriendData.value = none
    }

    private fun handleApproveFriend(none: None?) {
        approveFriendshipRequestData.value = none
    }
}