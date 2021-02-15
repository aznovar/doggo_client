package com.ru.appdoggo.domain.repository.friends

import com.ru.appdoggo.data.api.friends.FriendsRequests
import com.ru.appdoggo.data.cache.AccountCache
import com.ru.appdoggo.data.cache.friends.FriendsCache
import com.ru.appdoggo.domain.entities.friends.FriendsEntity
import com.ru.appdoggo.domain.type.*

class FriendsRepositoryImpl(
    private val friendsRequests: FriendsRequests,
    private val accountCache: AccountCache,
    private val friendsCache: FriendsCache
) : FriendsRepository {

    override fun addFriend(email: String): Either<Failure, None> {
        return accountCache.getAccount()
            .flatMap { friendsRequests.addFriend(email, it.id) }
    }

    override fun approveFriendshipRequest(friendsEntity: FriendsEntity): Either<Failure, None> {
        return accountCache.getAccount()
            .flatMap { friendsRequests.approveRequestToAFriends(it.id, friendsEntity.id) }
    }

    override fun getListOfFriendshipRequests(needFetch: Boolean): Either<Failure, List<FriendsEntity>> {
        return accountCache.getAccount()
            .flatMap {
                return@flatMap if (needFetch) {
                    friendsRequests.getRequestsToAFriend(it.id)
                } else {
                    Either.Right(friendsCache.getFriendRequests())
                }
            }
            .onNext { it.map {
                it.type = 1
                friendsCache.saveFriend(it)
            } }
    }

    override fun getFriendsList(needFetch: Boolean): Either<Failure, List<FriendsEntity>> {
        return accountCache.getAccount()
            .flatMap {
                return@flatMap if (needFetch) {
                    friendsRequests.getFriendsList(it.id)
                } else {
                    Either.Right(friendsCache.getFriends())
                }
            }
            .onNext { it.map { friendsCache.saveFriend(it) } }
    }
}