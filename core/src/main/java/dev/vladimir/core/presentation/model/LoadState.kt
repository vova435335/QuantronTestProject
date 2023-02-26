package dev.vladimir.core.presentation.model

sealed class LoadState<T>(val data: T? = null, val message: String? = null) {

    class Loading<T>(data: T? = null) : LoadState<T>(data)

    class Success<T>(data: T) : LoadState<T>(data)

    class Error<T>(message: String, data: T? = null) : LoadState<T>(data, message)
}
