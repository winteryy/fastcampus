package com.winterry.movieapp.features.detail.domain.usecase

import com.winterry.movieapp.features.common.entity.MovieDetailEntity

interface IGetMovieDetailUseCase {
    suspend operator fun invoke(name: String): MovieDetailEntity
}