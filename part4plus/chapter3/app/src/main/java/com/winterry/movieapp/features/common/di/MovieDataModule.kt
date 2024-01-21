package com.winterry.movieapp.features.common.di

import com.winterry.movieapp.features.common.network.api.IMovieAppNetworkApi
import com.winterry.movieapp.features.common.network.api.MovieAppNetworkApi
import com.winterry.movieapp.features.common.repository.IMovieDataSource
import com.winterry.movieapp.features.common.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieDataModule {

    @Singleton
    @Binds
    abstract fun bindMovieRepository(movieRepository: MovieRepository): IMovieDataSource

    @Singleton
    @Binds
    abstract fun bindNetwork(networkApi: MovieAppNetworkApi): IMovieAppNetworkApi
}