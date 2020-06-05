package com.ru.appdoggo.domain.entities.account

import com.google.gson.annotations.SerializedName

class AccountEntity(

    val id: Long,

    @SerializedName("username")
    val name: String,
    val status: String,
    val token: String,
    val registerDate: String

)

