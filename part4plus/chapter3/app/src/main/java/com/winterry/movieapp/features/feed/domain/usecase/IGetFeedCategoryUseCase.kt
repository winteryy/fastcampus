package com.winterry.movieapp.features.feed.domain.usecase

import com.winterry.movieapp.features.common.entity.CategoryEntity
import com.winterry.movieapp.features.common.entity.EntityWrapper
import com.winterry.movieapp.features.feed.domain.enum.SortOrder

interface IGetFeedCategoryUseCase {
    suspend operator fun invoke(sortOrder: SortOrder? = null): EntityWrapper<List<CategoryEntity>>
}
