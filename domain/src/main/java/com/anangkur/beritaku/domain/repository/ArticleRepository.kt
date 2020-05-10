package com.anangkur.beritaku.domain.repository

import androidx.lifecycle.LiveData
import com.anangkur.beritaku.core.base.BaseResult
import com.anangkur.beritaku.domain.model.Article

interface ArticleRepository {
    suspend fun clearArticle(category: String)
    suspend fun saveArticles(articles: List<Article>)
    fun getArticlesLocal(category: String): LiveData<List<Article>>
    suspend fun getArticleRemote(
        apiKey: String?,
        country: String?,
        category: String?,
        sources: String?,
        q: String?
    ): BaseResult<List<Article>>
}