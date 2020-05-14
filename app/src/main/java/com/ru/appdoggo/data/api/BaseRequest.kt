package com.ru.appdoggo.data.api

import com.ru.appdoggo.domain.type.Failure
import com.ru.appdoggo.handlers.network.NetworkHandler
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

/**
 * Класс отвечающий за проверку соединения и отправку запроса
 */
class BaseRequest @Inject constructor(private val networkHandler: NetworkHandler) {

    /**
     * Метод создающий запрос и проверяющий соединение. В случае нормального соединение запрос отправляется
     */
    fun <T : Any> make(call: Call<T>): NetworkResult<T> {
        return when (networkHandler.isConnected) {
            true -> execute(call)
            false, null -> NetworkResult.Error(IOException("WRONG"))
        }
    }


    private fun <T : Any> execute(call: Call<T>): NetworkResult<T> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> NetworkResult.Success(response.body()!!)
                false -> NetworkResult.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR -"))
            }
        } catch (exception: Throwable) {
            NetworkResult.Error(IOException("Server error"))
        }
    }
}
