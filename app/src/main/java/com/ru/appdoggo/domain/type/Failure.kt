package com.ru.appdoggo.domain.type

sealed class Failure{
    object NetworkConnectionError : Failure()
    object ServerError : Failure()
    object AuthError : Failure()
    object TokenError : Failure()

    object UsernameAlreadyExist : Failure()
    object EmailNotRegisteredError : Failure()
    object CantSendEmailError : Failure()

    object AlreadyFriendError : Failure()
    object AlreadyRequestedFriendError : Failure()
    object ContactNotFoundError : Failure()

    object NoSavedAccountsError : Failure()

    object FilePickError : Failure()
}