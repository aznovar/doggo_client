package com.ru.appdoggo.data.api.friends

import com.ru.appdoggo.data.api.ApiService
import com.ru.appdoggo.data.api.BaseRequest
import com.ru.appdoggo.data.api.account.AddFriendDataPost
import com.ru.appdoggo.data.api.account.ApproveFriendRequestDataPost
import com.ru.appdoggo.domain.entities.friends.FriendsEntity
import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.domain.type.None
import javax.inject.Inject

class FriendsRequestImpl @Inject constructor(
    private val apiService: ApiService,
    private val requests: BaseRequest
) : FriendsRequests {

    override fun addFriend(email: String, requestUserId: Long): Either<Failure, None> {
        return requests.make(apiService.addFriend(AddFriendDataPost(email, requestUserId)))
        { None() }
    }

    override fun approveRequestToAFriends(approveUserId: Long, requestUserId: Long): Either<Failure, None> {
        return requests.make(apiService.approveFriendsRequest(ApproveFriendRequestDataPost(approveUserId,requestUserId)))
        {None()}
    }

    override fun getRequestsToAFriend(userId: Long): Either<Failure, List<FriendsEntity>> {
        return requests.make(apiService.getRequestsToAFriend(userId)){it.listOfUsers}
    }

    override fun getFriendsList(userId: Long): Either<Failure, List<FriendsEntity>> {
        return requests.make(apiService.getFriendshipList(userId)){it.listOfUsers}
    }
}