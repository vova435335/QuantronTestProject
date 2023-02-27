package dev.vladimir.details.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.vladimir.details.data.MediaDetailsTmdbApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideMovieApi(retrofit: Retrofit): MediaDetailsTmdbApi =
        retrofit.create(MediaDetailsTmdbApi::class.java)
}
