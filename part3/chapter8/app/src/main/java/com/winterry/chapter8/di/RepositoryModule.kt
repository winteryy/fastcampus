package com.winterry.chapter8.di

import com.winterry.chapter8.data.repository.ContentRepositoryImpl
import com.winterry.chapter8.data.source.local.dao.ContentDao
import com.winterry.chapter8.data.source.remote.api.ContentService
import com.winterry.chapter8.domain.repository.ContentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun providesContentRepository(
        contentService: ContentService,
        contentDao: ContentDao
    ): ContentRepository = ContentRepositoryImpl(contentService, contentDao)
}