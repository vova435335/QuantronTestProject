package dev.vladimir.profile.presentation.data

import dev.vladimir.profile.presentation.data.response.ProfileResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface ProfileTmdbApi {

    @GET("account")
    suspend fun getProfile(): Response<ProfileResponseModel>
}
