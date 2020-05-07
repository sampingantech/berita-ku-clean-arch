package com.anangkur.beritaku.domain.repository

import androidx.lifecycle.LiveData
import com.anangkur.beritaku.core.base.BaseResult
import com.anangkur.beritaku.domain.model.Article

interface ArticleRepository {
    suspend fun clearArticle(category: String)
    suspend fun saveArticles(articles: List<Article>)
    fun getAllDataByCategory(category: String): LiveData<List<Article>>
    suspend fun getTopHeadlinesNews(
        apiKey: String?,
        country: String?,
        category: String?,
        sources: String?,
        q: String?
    ): BaseResult<List<Article>>
}