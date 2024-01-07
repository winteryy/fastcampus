package com.winterry.chapter8.data.repository

import com.winterry.chapter8.data.model.ContentMapper.toContent
import com.winterry.chapter8.data.model.ContentMapper.toEntity
import com.winterry.chapter8.data.model.ContentMapper.toRequest
import com.winterry.chapter8.data.source.local.dao.ContentDao
import com.winterry.chapter8.data.source.remote.api.ContentService
import com.winterry.chapter8.domain.model.Content
import com.winterry.chapter8.domain.repository.ContentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentService: ContentService,
    private val contentDao: ContentDao,
) : ContentRepository {

    override fun loadList(): Flow<List<Content>> {
        return flow {
            try {
                contentService.getList().data.also { list ->
                    contentDao.insertAll(list.map { it.toEntity() })
                }
            } finally {
                contentDao.selectAll().collect { list ->
                    emit(list.map { it.toContent() })
                }
            }
        }
    }

    override suspend fun save(item: Content): Boolean {
        return try {
            contentService.saveItem(item.toRequest()).also {
                if(it.success) {
                    it.data?.let { contentDto ->
                        contentDao.insert(contentDto.toEntity())
                    }
                }
            }
//            contentDao.insert(item.toEntity())
            true
        } catch (e: IOException) {
            false
        }
    }

    override suspend fun update(item: Content): Boolean {
        return try {
            contentService.updateItem(item.toRequest()).also {
                if(it.success) {
                    it.data?.let { contentDto ->
                        contentDao.insert(contentDto.toEntity())
                    }
                }
            }
//            contentDao.insert(item.toEntity())
            true
        } catch (e: IOException) {
            false
        }
    }

    override suspend fun delete(item: Content): Boolean {
        return try {
            item?.id?.let { id ->
                contentService.deleteItem(id).also {
                    if(it.success) {
                        contentDao.delete(item.toEntity())
                    }
                }
            }
//            contentDao.delete(item.toEntity())
            true
        } catch (e: IOException) {
            false
        }
    }

}