package com.ru.appdoggo.domain.usecases.friends

import com.ru.appdoggo.domain.repository.friends.FriendsRepository
import com.ru.appdoggo.domain.type.None
import com.ru.appdoggo.domain.usecases.UseCase
import javax.inject.Inject

class AddFriend @Inject constructor(
    private val friendsRepository: FriendsRepository
) : UseCase<None, AddFriend.Params>() {

    override suspend fun run(params: Params) = friendsRepository.addFriend(params.email)

    data class Params(val email: String)
}