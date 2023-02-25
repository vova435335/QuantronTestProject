package dev.vladimir.profile.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.vladimir.profile.data.TmdbAuthApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideMovieApi(retrofit: Retrofit): TmdbAuthApi =
        retrofit.create(TmdbAuthApi::class.java)
}
