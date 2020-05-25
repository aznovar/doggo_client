package com.ru.appdoggo.data.api.account

import com.ru.appdoggo.data.api.BaseResponse
import com.ru.appdoggo.domain.entities.account.AccountEntity

class AuthResponse(
    success: Int,
    message: String,
    val user: AccountEntity
) : BaseResponse(success, message)