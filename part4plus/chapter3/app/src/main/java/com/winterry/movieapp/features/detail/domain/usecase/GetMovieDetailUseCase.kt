package com.winterry.movieapp.features.detail.domain.usecase

import com.winterry.movieapp.features.common.entity.MovieDetailEntity
import com.winterry.movieapp.features.common.repository.IMovieDataSource
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val dataSource: IMovieDataSource
) : IGetMovieDetailUseCase {
    override suspend fun invoke(name: String): MovieDetailEntity {
        return dataSource.getMovieDetail(name)
    }
}