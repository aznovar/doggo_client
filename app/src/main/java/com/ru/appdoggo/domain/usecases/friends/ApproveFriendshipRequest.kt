package com.ru.appdoggo.domain.usecases.friends

import com.ru.appdoggo.domain.entities.friends.FriendsEntity
import com.ru.appdoggo.domain.repository.friends.FriendsRepository
import com.ru.appdoggo.domain.type.None
import com.ru.appdoggo.domain.usecases.UseCase
import javax.inject.Inject

class ApproveFriendshipRequest @Inject constructor(
    private val friendsRepository: FriendsRepository
) : UseCase<None, FriendsEntity>() {

    override suspend fun run(params: FriendsEntity) =
        friendsRepository.approveFriendshipRequest(params)
}