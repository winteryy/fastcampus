package com.winterry.chapter7.repository

import com.winterry.chapter7.model.ContentEntity
import kotlinx.coroutines.flow.Flow

interface ContentRepository {

    fun loadList(): Flow<List<ContentEntity>>

    suspend fun insert(item: ContentEntity)

    suspend fun modify(item: ContentEntity)

    suspend fun delete(item: ContentEntity)
}