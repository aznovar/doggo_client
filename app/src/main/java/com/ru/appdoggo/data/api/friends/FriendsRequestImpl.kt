package com.ru.appdoggo.data.api.friends

import com.ru.appdoggo.data.api.ApiService
import com.ru.appdoggo.data.api.BaseRequest
import com.ru.appdoggo.data.api.account.AccountRequests
import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.domain.type.None
import javax.inject.Inject

class FriendsRequestImpl @Inject constructor(
    private val apiService: ApiService,
    private val requests: BaseRequest
) : FriendsRequests {

    override fun addFriend(email: String, requestUserId: Long): Either<Failure, None> {
        TODO("Not yet implemented")
    }

    override fun approveRequestToAFriends(approveUserId: Long, requestUserId: Long) : Either<Failure, None>{
        TODO("Not yet implemented")
    }

    override fun getRequestsToAFriend(userId: Long): Either<Failure, List<FriendsEntity>> {
        TODO("Not yet implemented")
    }
}