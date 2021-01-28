package com.android.towndirectory.core.utils

import android.util.Log
import com.android.towndirectory.R
import com.android.towndirectory.core.TownApplication
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException

private const val TAG = "ErrorHelper"
private val applicationContext = TownApplication.context

object ErrorCodes {
    const val INVALID_REQUEST_DATA = 1
    const val REQUEST_ERROR = 2
}

fun handleNetworkException(throwable: Throwable): ErrorEntity {
    return when (throwable) {
        is IOException -> ErrorEntity.Network(throwable)
        is SocketTimeoutException -> ErrorEntity.SocketTimeOut(throwable)
        is KotlinNullPointerException -> ErrorEntity.EmptyResponse(throwable)
        is HttpException -> {
            when (throwable.code()) {
                // not found
                HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound(throwable)
                // access denied
                HttpURLConnection.HTTP_FORBIDDEN -> ErrorEntity.AccessDenied(throwable)
                // unavailable service
                HttpURLConnection.HTTP_UNAVAILABLE -> ErrorEntity.ServiceUnavailable(throwable)
                // unauthorized request
                HttpURLConnection.HTTP_UNAUTHORIZED -> ErrorEntity.InvalidToken(throwable)
                // all the others will be treated as unknown error
                else -> ErrorEntity.Unknown(throwable)
            }
        }
        else -> {
            throwable.message?.let { Log.e(TAG, it) }
            ErrorEntity.Unknown(throwable)
        }
    }
}

fun handleBusinessError(errorCode: Int, errorMessage: String = ""): ErrorEntity {
    return when (errorCode) {
        ErrorCodes.INVALID_REQUEST_DATA ->
            ErrorEntity.InvalidRequestData(
                Error(
                    if (errorMessage.isEmpty())
                        applicationContext.getString(R.string.error_invalid_parameters)
                    else errorMessage
                )
            )
        ErrorCodes.REQUEST_ERROR ->
            ErrorEntity.FailureResponse(
                Error(
                    if (errorMessage.isEmpty())
                        applicationContext.getString(R.string.error_request)
                    else errorMessage
                )
            )
        else -> {
            ErrorEntity.Unknown(
                Error(
                    if (errorMessage.isEmpty())
                        applicationContext.getString(R.string.error_request) else errorMessage
                )
            )
        }
    }
}