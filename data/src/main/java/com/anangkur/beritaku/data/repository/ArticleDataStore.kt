package com.anangkur.beritaku.data.repository

import androidx.lifecycle.LiveData
import com.anangkur.beritaku.core.BaseResult
import com.anangkur.beritaku.data.model.ArticleEntity

interface ArticleDataStore {
    suspend fun insertData(data: List<ArticleEntity>)
    suspend fun deleteByCategory(category: String)
    fun getAllDataByCategory(category: String): LiveData<List<ArticleEntity>>
    suspend fun getTopHeadlinesNews(
        apiKey: String?,
        country: String?,
        category: String?,
        sources: String?,
        q: String?
    ): BaseResult<List<ArticleEntity>>
}