package com.ru.appdoggo.data.api

import com.ru.appdoggo.data.api.account.AuthResponse
import com.ru.appdoggo.data.api.account.UsernameDataPost
import retrofit2.Call
import retrofit2.http.*

/**
 * Интерфейс описывающий эндпоинты и http методы взаимодействия с ними
 */
interface ApiService {

    companion object {
        const val REGISTRATION_ENDPOINT = "registration"
        const val LOGIN_ENDPOINT = "auth/login"
    }

    @Headers("Content-Type: application/json")
    @POST(REGISTRATION_ENDPOINT)
    fun register(@Body registerDataPost: UsernameDataPost): Call<BaseResponse>

    @Headers("Content-Type: application/json")
    @POST(LOGIN_ENDPOINT)
    fun login(@Body loginDataPost: UsernameDataPost): Call<AuthResponse>
}


