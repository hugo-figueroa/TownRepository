package com.android.towndirectory.core.utils

import android.content.Context
import com.android.towndirectory.R
import com.android.towndirectory.core.TownApplication
import retrofit2.Response
import java.io.IOException

open class BaseNetwork {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, error: String): Result<T> {
        val applicationContext = TownApplication.context
        return if (isNetworkWorking(applicationContext)) {
            genericApiOutput(call, error, applicationContext)
        } else {
            Result.Failure(
                handleNetworkException(
                    IOException(
                        applicationContext.getString(R.string.error_config_NoNetworkConnection)
                    )
                )
            )
        }
    }

    private suspend fun <T : Any> genericApiOutput(
        call: suspend () -> Response<T>,
        error: String, applicationContext: Context
    ): Result<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                Result.Success(response.body()!!)
            } else {
                Result.Failure(
                    handleBusinessError(
                        ErrorCodes.REQUEST_ERROR,
                        String.format(applicationContext.getString(R.string.exception), error)
                    )
                )
            }
        } catch (throwable: Throwable) {
            Result.Failure(handleNetworkException(throwable))
        }
    }
}