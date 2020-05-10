package com.anangkur.beritaku.data.source

import androidx.lifecycle.LiveData
import com.anangkur.beritaku.core.base.BaseResult
import com.anangkur.beritaku.data.model.ArticleEntity
import com.anangkur.beritaku.data.repository.ArticleDataStore
import com.anangkur.beritaku.data.repository.ArticleRemote
import javax.inject.Inject

class ArticleRemoteDataStore (private val articleRemote: ArticleRemote): ArticleDataStore {
    override suspend fun insertData(data: List<ArticleEntity>) {
        throw UnsupportedOperationException()
    }

    override suspend fun deleteByCategory(category: String) {
        throw UnsupportedOperationException()
    }

    override fun getAllDataByCategory(category: String): LiveData<List<ArticleEntity>> {
        throw UnsupportedOperationException()
    }

    override suspend fun getTopHeadlinesNews(
        apiKey: String?,
        country: String?,
        category: String?,
        sources: String?,
        q: String?
    ): BaseResult<List<ArticleEntity>> {
        return articleRemote.getTopHeadlinesNews(apiKey, country, category, sources, q)
    }
}