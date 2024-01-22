package com.winterry.movieapp.library.storage.di

import com.winterry.movieapp.library.storage.IStorage
import com.winterry.movieapp.library.storage.StorageManager
import com.winterry.movieapp.library.storage.helpers.DataConverter
import com.winterry.movieapp.library.storage.helpers.DataEncoding
import com.winterry.movieapp.library.storage.prefs.SharedPrefsStorageProvider
import com.winterry.movieapp.library.storage.prefs.StorageProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Singleton
    @Provides
    fun bindOnDiscStorage(
        storage: StorageProvider,
        converter: DataConverter,
        encoding: DataEncoding
    ): IStorage = StorageManager(storage, converter, encoding)

    @Provides
    fun provideSharePrefStorageProvider(provider: SharedPrefsStorageProvider): StorageProvider =
        provider

}