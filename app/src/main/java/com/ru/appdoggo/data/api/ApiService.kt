package com.ru.appdoggo.data.api

import com.ru.appdoggo.data.api.account.AddFriendDataPost
import com.ru.appdoggo.data.api.account.ApproveFriendRequestDataPost
import com.ru.appdoggo.data.api.account.AuthResponse
import com.ru.appdoggo.data.api.account.UsernameDataPost
import com.ru.appdoggo.data.api.friends.GetFriendshipRequestResponse
import retrofit2.Call
import retrofit2.http.*

/**
 * Интерфейс описывающий эндпоинты и http методы взаимодействия с ними
 */
interface ApiService {

    companion object {
        const val REGISTRATION_ENDPOINT = "registration"
        const val LOGIN_ENDPOINT = "auth/login"
        const val ADD_FRIEND_ENDPOINT = "todo"
        const val APPROVE_FRIEND_ENDPOINT = "todo"
        const val GET_LIST_OF_FRIEND_REQUESTS = "todo"
    }

    @Headers("Content-Type: application/json")
    @POST(REGISTRATION_ENDPOINT)
    fun register(@Body registerDataPost: UsernameDataPost): Call<BaseResponse>

    @Headers("Content-Type: application/json")
    @POST(LOGIN_ENDPOINT)
    fun login(@Body loginDataPost: UsernameDataPost): Call<AuthResponse>

    @Headers("Content-Type: application/json")
    @POST(ADD_FRIEND_ENDPOINT)
    fun addFriend(@Body addFriendDataPost: AddFriendDataPost): Call<BaseResponse>

    @Headers("Content-Type: application/json")
    @POST(APPROVE_FRIEND_ENDPOINT)
    fun approveFriendsRequest(@Body approveFriendRequestDataPost: ApproveFriendRequestDataPost): Call<BaseResponse>

    @Headers("Content-Type: application/json")
    @GET(GET_LIST_OF_FRIEND_REQUESTS)
    fun getRequestsToAFriend(@Body userId: Long): Call<GetFriendshipRequestResponse>
}


