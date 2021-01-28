package com.android.towndirectory.core.utils

sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()

    /**
     * @param error An exception thrown by Retrofit.
     */
    data class Failure(val error: ErrorEntity) : Result<Nothing>()
}