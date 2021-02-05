package com.ru.appdoggo.data.api.friends

import com.ru.appdoggo.domain.entities.friends.FriendsEntity
import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.domain.type.None

interface FriendsRequests {

    fun addFriend(email: String, requestUserId : Long) : Either<Failure, None>

    fun approveRequestToAFriends(approveUserId : Long, requestUserId: Long): Either<Failure, None>

    fun getRequestsToAFriend(userId : Long): Either<Failure, List<FriendsEntity>>

    fun getFriendsList(userId: Long): Either<Failure, List<FriendsEntity>>
}