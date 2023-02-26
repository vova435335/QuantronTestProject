package dev.vladimir.auth.data

import dev.vladimir.auth.data.request.RequestSessionBody
import dev.vladimir.auth.data.request.RequestToken
import dev.vladimir.auth.data.request.RequestTokenBody
import dev.vladimir.auth.data.request.Session
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TmdbAuthApi {

    @POST("authentication/token/validate_with_login")
    suspend fun validateRequestTokenWithLogin(@Body requestTokenBody: RequestTokenBody): RequestToken

    @POST("authentication/session/new")
    suspend fun createSessionId(@Body requestToken: RequestSessionBody): Session

    @GET("authentication/token/new")
    suspend fun createRequestToken(): RequestToken
}
