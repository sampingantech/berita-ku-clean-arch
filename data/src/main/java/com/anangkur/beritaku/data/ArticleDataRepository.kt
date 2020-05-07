package com.anangkur.beritaku.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.anangkur.beritaku.core.base.BaseResult
import com.anangkur.beritaku.data.mapper.ArticleMapper
import com.anangkur.beritaku.data.source.ArticleDataStoreFactory
import com.anangkur.beritaku.domain.model.Article
import com.anangkur.beritaku.domain.repository.ArticleRepository
import javax.inject.Inject

class ArticleDataRepository @Inject constructor(
    private val factory: ArticleDataStoreFactory,
    private val mapper: ArticleMapper
): ArticleRepository {
    override suspend fun clearArticle(category: String) {
        factory.retrieveCacheDataStore().deleteByCategory(category)
    }

    override suspend fun saveArticles(articles: List<Article>) {
        factory.retrieveCacheDataStore().insertData(articles.map { mapper.mapToEntity(it) })
    }

    override fun getAllDataByCategory(category: String): LiveData<List<Article>> {
        return factory.retrieveCacheDataStore().getAllDataByCategory(category).map { list -> list.map { mapper.mapFromEntity(it) } }
    }

    override suspend fun getTopHeadlinesNews(
        apiKey: String?,
        country: String?,
        category: String?,
        sources: String?,
        q: String?
    ): BaseResult<List<Article>> {
        val result = factory.retrieveRemoteDataStore().getTopHeadlinesNews(apiKey, country, category, sources, q)
        return BaseResult(result.status, result.data?.map { mapper.mapFromEntity(it) }, result.message, result.isLoading)
    }

}