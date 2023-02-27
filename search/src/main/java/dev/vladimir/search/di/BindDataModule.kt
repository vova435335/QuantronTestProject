package dev.vladimir.search.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.vladimir.search.data.repository.SearchRepository
import dev.vladimir.search.domain.repository.ISearchRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BindDataModule {

    @Singleton
    @Binds
    fun bindRepository(
        searchRepository: SearchRepository,
    ): ISearchRepository
}