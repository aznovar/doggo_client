package com.ru.appdoggo.data.api.friends

import com.google.gson.annotations.SerializedName
import com.ru.appdoggo.data.api.BaseResponse
import com.ru.appdoggo.domain.entities.friends.FriendsEntity

class GetFriendshipRequestResponse(
    success: Int,
    message: String,
    @SerializedName("friend_requests")
    val friendsRequests: List<FriendsEntity>
) : BaseResponse(success,message)