package dev.vladimir.profile.data

import dev.vladimir.profile.data.request.RequestSessionBody
import dev.vladimir.profile.data.request.RequestToken
import dev.vladimir.profile.data.request.RequestTokenBody
import dev.vladimir.profile.data.request.Session
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
