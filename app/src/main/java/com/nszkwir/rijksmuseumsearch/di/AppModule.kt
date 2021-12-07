package com.nszkwir.rijksmuseumsearch.di

import com.nszkwir.rijksmuseumsearch.common.network.ApiClient
import com.nszkwir.rijksmuseumsearch.data.art_objects.repository.ArtObjectRepository
import com.nszkwir.rijksmuseumsearch.data.art_objects.repository.ArtObjectRepositoryImpl
import com.nszkwir.rijksmuseumsearch.data.art_objects.repository.ArtObjectService
import com.nszkwir.rijksmuseumsearch.domain.MainUseCasesImpl
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
    fun provideArtObjectRepository(
        service: ArtObjectService
    ): ArtObjectRepository =
        ArtObjectRepositoryImpl(service)

    @Provides
    @Singleton
    fun provideMainUseCases(
        repository: ArtObjectRepository
    ): MainUseCasesImpl =
        MainUseCasesImpl(repository)

    @Provides
    @Singleton
    fun provideApiClient(): ApiClient =
        ApiClient()

    @Provides
    @Singleton
    fun provideArtObjectService(apiClient: ApiClient): ArtObjectService =
        apiClient.createService(ArtObjectService::class.java)
}
