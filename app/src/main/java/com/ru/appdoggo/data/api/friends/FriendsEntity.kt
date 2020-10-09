package com.ru.appdoggo.data.api.friends

data class FriendsEntity(
    var id: Long,
    var name: String,
    var status: String,
    var isRequest: Int = 0
)