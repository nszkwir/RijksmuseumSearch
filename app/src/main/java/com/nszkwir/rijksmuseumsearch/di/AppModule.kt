package com.nszkwir.rijksmuseumsearch.di

import com.nszkwir.rijksmuseumsearch.domain.MainUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMainUseCases(
    ): MainUseCases =
        MainUseCases()
}