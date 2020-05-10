package com.anangkur.beritaku.data.source

import androidx.lifecycle.LiveData
import com.anangkur.beritaku.core.base.BaseResult
import com.anangkur.beritaku.data.model.ArticleEntity
import com.anangkur.beritaku.data.repository.ArticleDataStore
import com.anangkur.beritaku.data.repository.ArticleLocal
import javax.inject.Inject

class ArticleLocalDataStore (private val articleLocal: ArticleLocal): ArticleDataStore {
    override suspend fun insertData(data: List<ArticleEntity>) {
        articleLocal.insertData(data)
    }

    override suspend fun deleteByCategory(category: String) {
        articleLocal.deleteByCategory(category)
    }

    override fun getAllDataByCategory(category: String): LiveData<List<ArticleEntity>> {
        return articleLocal.getAllDataByCategory(category)
    }

    override suspend fun getTopHeadlinesNews(
        apiKey: String?,
        country: String?,
        category: String?,
        sources: String?,
        q: String?
    ): BaseResult<List<ArticleEntity>> {
        throw UnsupportedOperationException()
    }

}