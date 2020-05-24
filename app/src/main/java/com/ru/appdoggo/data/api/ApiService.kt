package com.ru.appdoggo.data.api

import retrofit2.Call
import retrofit2.http.*

/**
 * Интерфейс описывающий эндпоинты и http методы взаимодействия с ними
 */
interface ApiService {

    companion object {
        const val REGISTRATION_ENDPOINT = "registration"
        const val LOGIN_ENDPOINT = "auth/login"

        const val USERNAME_PARAM = "username"
        const val PASSWORD_PARAM = "password"
    }

    @Headers("Content-Type: application/json")
    @POST(REGISTRATION_ENDPOINT)
    fun register(@Body registerDataPost: RegisterDataPost): Call<BaseResponse>
}

data class RegisterDataPost(val username: String, val password: String)
