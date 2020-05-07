package com.anangkur.beritaku.data.repository

import com.anangkur.beritaku.core.base.BaseResult
import com.anangkur.beritaku.data.model.ArticleEntity

interface ArticleRemote {
    suspend fun getTopHeadlinesNews(
        apiKey: String?,
        country: String?,
        category: String?,
        sources: String?,
        q: String?
    ): BaseResult<List<ArticleEntity>>
}