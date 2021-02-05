package com.ru.appdoggo.domain.repository.friends

import com.ru.appdoggo.domain.entities.friends.FriendsEntity
import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.domain.type.None

interface FriendsRepository {

    fun addFriend(email: String): Either<Failure, None>

    fun approveFriendshipRequest(approveFriends: FriendsEntity): Either<Failure, None>

    fun getListOfFriendshipRequests(): Either<Failure, List<FriendsEntity>>

    fun getFriendsList(): Either<Failure, List<FriendsEntity>>
}