package com.ru.appdoggo.domain.entities.account

import com.google.gson.annotations.SerializedName

class AccountEntity(
    @SerializedName("username")
    val name: String,

    val token: String

)

