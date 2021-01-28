package com.android.towndirectory.core.utils

import androidx.annotation.NonNull

data class Resource<T>(val status: Int, val data: T?, val errorType: Int?) {

    companion object {
        const val STATUS_LOADING = 0
        const val STATUS_SUCCESS = 1
        const val STATUS_ERROR = -1

        /**
         * Helper method to create fresh state resource
         */
        fun <T> success(@NonNull data: T): Resource<T> {
            return Resource(STATUS_SUCCESS, data, null)
        }

        /**
         * Helper method to create error state Resources. Error state might also have the current data, if any
         */
        fun <T> error(item: T? = null): Resource<T> {
            return Resource(STATUS_ERROR, item, null)
        }

        /**
         * Helper method to create loading state Resources.
         */
        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(STATUS_LOADING, data, null)
        }
    }
}