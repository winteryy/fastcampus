package com.winterry.movieapp.features.common.network.api

import com.winterry.movieapp.features.common.network.model.MovieResponse
import com.winterry.movieapp.library.network.model.ApiResult

interface IMovieAppNetworkApi {
    suspend fun getMovies(): ApiResult<List<MovieResponse>>
}