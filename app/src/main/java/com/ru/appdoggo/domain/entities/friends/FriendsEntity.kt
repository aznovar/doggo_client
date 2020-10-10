package com.ru.appdoggo.domain.entities.friends

data class FriendsEntity(
    var id: Long,
    var name: String,
    var status: String,
    var isRequest: Int = 0
)