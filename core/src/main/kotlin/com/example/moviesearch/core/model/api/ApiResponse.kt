package com.example.moviesearch.core.model.api

import com.example.moviesearch.core.model.api.Status.ERROR
import com.example.moviesearch.core.model.api.Status.SUCCESS

/**
 * ApiResponse holder provided to the UI
 *
 * @param <T>
 */
class ApiResponse<T>
constructor(val status: Status, val data: T? = null,
            val error: Throwable? = null, val isLoading: Boolean = false) {
    companion object {

        fun <T> success(data: T): ApiResponse<T> {
            return ApiResponse(SUCCESS, data)
        }

        fun <T> error(error: Throwable): ApiResponse<T> {
            return ApiResponse(ERROR, error = error)
        }

    }
}
