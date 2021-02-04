package com.ru.appdoggo.data.api.friends

import com.google.gson.annotations.SerializedName
import com.ru.appdoggo.data.api.BaseResponse
import com.ru.appdoggo.domain.entities.friends.FriendsEntity

class GetFriendshipRequestResponse(
    success: Int,
    message: String,
    val friendsRequest: List<FriendsEntity>
) : BaseResponse(success,message)