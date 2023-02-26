package dev.vladimir.profile.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.vladimir.profile.presentation.data.ProfileTmdbApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideProfileTmdbIpi(retrofit: Retrofit): ProfileTmdbApi =
        retrofit.create(ProfileTmdbApi::class.java)
}
