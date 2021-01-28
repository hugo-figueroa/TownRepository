package com.android.towndirectory.core.utils

inline fun <T : Any> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
    if (this is Result.Success) action(data)
    return this
}

inline fun <T : Any, R : Any> Result<T>.mapOnSuccess(map: (T) -> R) = when (this) {
    is Result.Success -> Result.Success(map(data))
    is Result.Failure -> this
}

inline fun <T : Any> Result<T>.onFailure(action: (ErrorEntity) -> Unit) {
    if (this is Result.Failure) action(error)
}