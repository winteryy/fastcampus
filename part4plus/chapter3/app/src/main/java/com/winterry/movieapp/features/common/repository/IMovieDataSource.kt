package com.winterry.movieapp.features.common.repository

interface IMovieDataSource {
    suspend fun getMovieList()
}