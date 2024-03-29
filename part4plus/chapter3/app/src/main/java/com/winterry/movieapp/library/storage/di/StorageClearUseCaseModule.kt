package com.winterry.movieapp.library.storage.di

import com.winterry.movieapp.library.storage.usecases.IStorageClearUseCase
import com.winterry.movieapp.library.storage.usecases.StorageClearUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageClearUseCaseModule {

    @Binds
    @Singleton
    abstract fun bindStorageClearUseCase(
        storageClearUserCase: StorageClearUseCase
    ): IStorageClearUseCase
}