package com.ru.appdoggo.domain.usecases

import com.ru.appdoggo.domain.repository.account.AccountRepository
import com.ru.appdoggo.domain.type.Either
import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.domain.type.None
import javax.inject.Inject

class Register @Inject constructor(
    private val repository: AccountRepository
) : UseCase<None, Register.Params>() {


    override suspend fun run(params: Params): Either<Failure, None> {
        return repository.register(params.username, params.password)
    }

    data class Params( val username: String, val password: String)

}