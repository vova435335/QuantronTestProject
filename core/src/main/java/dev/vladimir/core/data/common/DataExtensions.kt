package dev.vladimir.core.data.common

import retrofit2.Response
import dev.vladimir.core.data.common.models.Result

fun <T> Response<T>.toResult(): Result<T> {
    val body = body()
    return when {
        isSuccessful && body != null -> Result.Success(body)
        else -> Result.Error()
    }
}

fun <T, R> Result<T>.map(
    ifSuccess: ((T) -> R)? = null,
    ifError: (() -> String) = { "" },
): Result<R> = when {
    this is Result.Success && ifSuccess != null -> Result.Success(ifSuccess(data))
    else -> Result.Error(ifError.invoke())
}
