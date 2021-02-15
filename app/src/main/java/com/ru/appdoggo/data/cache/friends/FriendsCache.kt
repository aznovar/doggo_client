package com.ru.appdoggo.data.cache.friends

import com.ru.appdoggo.domain.entities.friends.FriendsEntity

interface FriendsCache {

    fun saveFriend(entity: FriendsEntity)

    fun getFriend(key: Long): FriendsEntity?

    fun getFriends(): List<FriendsEntity>

    fun getFriendRequests(): List<FriendsEntity>
}