package com.ru.appdoggo.data.api

import com.ru.appdoggo.data.api.account.*
import com.ru.appdoggo.data.api.chats.GetChatsByUserResponse
import com.ru.appdoggo.data.api.chats.GetMessagesByUserResponse
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
        const val ADD_FRIEND_ENDPOINT = "friendship/addFriend"
        const val APPROVE_FRIENDSHIP_ENDPOINT = "friendship/approveFriendship"
        const val GET_LIST_OF_FRIENDS_REQUESTS = "friendship/getRequests/{id}"
        const val GET_LIST_FRIENDS = "friendship/getFriendshipList/{id}"
        const val SEND_MESSAGE = "messaging/sendMessage"
        const val GET_CHATS_BY_USER = "messaging/getChatsByUser/{userId}"
        const val GET_MESSAGES_WITH_CONTACT = "messaging/getMessagesWithContact/{contactId}/{userId}"
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
    @POST(APPROVE_FRIENDSHIP_ENDPOINT)
    fun approveFriendsRequest(@Body approveFriendRequestDataPost: ApproveFriendRequestDataPost): Call<BaseResponse>

    @Headers("Content-Type: application/json")
    @GET(GET_LIST_FRIENDS)
    fun getFriendshipList(@Path("id") userId: Long): Call<GetFriendshipRequestResponse>

    @Headers("Content-Type: application/json")
    @GET(GET_LIST_OF_FRIENDS_REQUESTS)
    fun getRequestsToAFriend(@Path("id") userId: Long): Call<GetFriendshipRequestResponse>

    @Headers("Content-Type: application/json")
    @POST(SEND_MESSAGE)
    fun sendMessage(@Body sendMessageDataPost: SendMessageDataPost): Call<BaseResponse>

    @Headers("Content-Type: application/json")
    @GET(GET_CHATS_BY_USER)
    fun getChatsByUser(@Path("userId")userId: Long): Call<GetChatsByUserResponse>

    @Headers("Content-Type: application/json")
    @GET(GET_MESSAGES_WITH_CONTACT)
    fun getMessagesWithContact(@Path("contactId")contactId: Long, @Path("userId")userId: Long): Call<GetMessagesByUserResponse>
}


