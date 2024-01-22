package com.winterry.movieapp.features.feed.presentation.output

import com.winterry.movieapp.features.common.entity.CategoryEntity
import com.winterry.movieapp.features.common.entity.MovieFeedItemEntity

sealed class FeedState {
    object Loading: FeedState()
    class Main(
        val categories: List<CategoryEntity>
    ): FeedState()
    class Failed(
        val reason: String
    ): FeedState()
}