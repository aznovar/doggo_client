package com.ru.appdoggo.data.api.account

import com.ru.appdoggo.data.api.ApiService
import com.ru.appdoggo.data.api.BaseRequest
import com.ru.appdoggo.domain.entities.account.AccountEntity
import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.domain.type.None
import javax.inject.Inject

/**
 * Имплементация AccountRequests
 */
class AccountRequestsImpl @Inject constructor(
    private val apiService: ApiService,
    private val requests: BaseRequest
) : AccountRequests {

    override fun register(username: String, password: String): Either<Failure, None> {
        return requests.make(
            apiService.register(UsernameDataPost(username, password))
        ) { None() }
    }

    override fun login(username: String, password: String): Either<Failure, AccountEntity> {
        return requests.make(apiService.login(UsernameDataPost(username, password))) { it.user }
    }

}