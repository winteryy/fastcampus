package com.winterry.movieapp.features.common.repository

import com.winterry.movieapp.features.common.entity.CategoryEntity
import com.winterry.movieapp.features.common.entity.EntityWrapper
import com.winterry.movieapp.features.common.entity.MovieDetailEntity
import com.winterry.movieapp.features.feed.domain.enum.SortOrder

interface IMovieDataSource {
    suspend fun getCategories(sortOrder: SortOrder? = null): EntityWrapper<List<CategoryEntity>>
    suspend fun getMovieDetail(movieName: String): MovieDetailEntity
}