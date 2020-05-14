package com.ru.appdoggo.data.api

/**
 * Изолированный класс служащий для формального определения успеха\неуспеха отправленного запроса
 */
sealed class NetworkResult<out T: Any> {

    data class Success<out T : Any>(val data: T) : NetworkResult<T>()

    data class Error(val exception: Exception) : NetworkResult<Nothing>()

}