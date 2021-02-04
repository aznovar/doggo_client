package com.ru.appdoggo.domain.entities.friends

import com.google.gson.annotations.SerializedName

class FriendsEntity(
    @SerializedName("username")
    val name: String,

    val id: Long,

    val type: Int
)