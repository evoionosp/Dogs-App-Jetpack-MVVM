package com.evoionosp.dogs.data.repo

import com.evoionosp.dogs.domain.repo.DogsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDogsRepository(
        dogsRepositoryImpl: DogsRepositoryImpl
    ): DogsRepository

}