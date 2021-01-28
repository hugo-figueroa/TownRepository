package com.android.towndirectory.core.utils

sealed class ErrorEntity {

    abstract val originalException: Throwable

    data class Network(override val originalException: Throwable) : ErrorEntity()

    data class NotFound(override val originalException: Throwable) : ErrorEntity()

    data class AccessDenied(override val originalException: Throwable) : ErrorEntity()

    data class ServiceUnavailable(override val originalException: Throwable) : ErrorEntity()

    data class SocketTimeOut(override val originalException: Throwable) : ErrorEntity()

    data class InvalidToken(override val originalException: Throwable) : ErrorEntity()

    data class InvalidRequestData(override val originalException: Throwable) : ErrorEntity()

    data class FailureResponse(override val originalException: Throwable) : ErrorEntity()

    data class EmptyResponse(override val originalException: Throwable) : ErrorEntity()

    data class Unknown(override val originalException: Throwable) : ErrorEntity()
}