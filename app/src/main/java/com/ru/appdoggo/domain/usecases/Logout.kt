package com.ru.appdoggo.domain.usecases

import com.ru.appdoggo.domain.repository.account.AccountRepository
import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.domain.type.None
import javax.inject.Inject

class Logout @Inject constructor(
    private val accountRepository: AccountRepository
) : UseCase<None, None>() {

    override suspend fun run(params: None): Either<Failure, None> = accountRepository.logout()

}