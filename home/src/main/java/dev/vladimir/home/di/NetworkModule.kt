package dev.vladimir.home.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.vladimir.home.data.PopularMediaTmdbApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideMovieApi(retrofit: Retrofit): PopularMediaTmdbApi =
        retrofit.create(PopularMediaTmdbApi::class.java)
}
