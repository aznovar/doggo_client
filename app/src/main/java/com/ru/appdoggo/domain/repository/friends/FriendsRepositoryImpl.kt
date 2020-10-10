package com.ru.appdoggo.domain.repository.friends

import com.ru.appdoggo.data.api.friends.FriendsRequests
import com.ru.appdoggo.data.cache.AccountCache
import com.ru.appdoggo.domain.entities.friends.FriendsEntity
import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.domain.type.None
import com.ru.appdoggo.domain.type.flatMap

class FriendsRepositoryImpl(
    private val friendsRequests: FriendsRequests,
    private val accountCache: AccountCache
) : FriendsRepository {

    override fun addFriend(email: String): Either<Failure, None> {
        return accountCache.getAccount()
            .flatMap { friendsRequests.addFriend(email,it.id) }
    }

    override fun approveFriendshipRequest(friendsEntity: FriendsEntity): Either<Failure, None> {
        return accountCache.getAccount()
            .flatMap { friendsRequests.approveRequestToAFriends(it.id, friendsEntity.id) }
    }

    override fun getListOfFriendshipRequests(userId: Long): Either<Failure, List<FriendsEntity>> {
        return accountCache.getAccount()
            .flatMap { friendsRequests.getRequestsToAFriend(it.id) }
    }
}