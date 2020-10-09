package com.ru.appdoggo.data.api.account

data class UsernameDataPost(val username: String, val password: String)

data class AddFriendDataPost(val email: String, val userId: Long)

data class ApproveFriendRequestDataPost(val approveUserId : Long, val requestUserId: Long)