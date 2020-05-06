package com.anangkur.beritaku.data

import androidx.lifecycle.LiveData
import com.anangkur.beritaku.core.base.BaseResult
import com.anangkur.beritaku.data.model.ArticleEntity

interface DataSource {
    suspend fun getTopHeadlinesNews(
        apiKey: String?,
        country: String?,
        category: String?,
        sources: String?,
        q: String?
    ): BaseResult<List<ArticleEntity>> { throw Exception() }

    suspend fun insertData(data: List<ArticleEntity>) { throw Exception() }
    suspend fun deleteByCategory(category: String) { throw Exception() }
    fun getAllDataByCategory(category: String): LiveData<List<ArticleEntity>> { throw Exception() }
}