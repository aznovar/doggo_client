package com.ru.appdoggo.data.api

import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Интерфейс описывающий эндпоинты и http методы взаимодействия с ними
 */
interface ApiService {

    companion object {
        const val REGISTRATION_ENDPOINT = "/registration"
        const val LOGIN_ENDPOINT = "/auth/login"

        const val USERNAME_PARAM = "username"
        const val PASSWORD_PARAM = "password"
    }

    @FormUrlEncoded
    @POST(REGISTRATION_ENDPOINT)
    fun register(@FieldMap params: Map<String, String>): Call<String>
}