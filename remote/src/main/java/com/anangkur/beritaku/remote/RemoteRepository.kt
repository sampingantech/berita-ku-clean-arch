package com.anangkur.beritaku.remote

import com.anangkur.beritaku.data.BaseDataSource
import com.anangkur.beritaku.data.DataSource
import com.anangkur.beritaku.core.base.BaseResult
import com.anangkur.beritaku.data.model.ArticleEntity
import com.anangkur.beritaku.remote.mapper.ArticleMapper

class RemoteRepository(private val mapper: ArticleMapper): DataSource, BaseDataSource() {

    override suspend fun getTopHeadlinesNews(
        apiKey: String?,
        country: String?,
        category: String?,
        sources: String?,
        q: String?
    ): com.anangkur.beritaku.core.base.BaseResult<List<ArticleEntity>> {
        val response = ApiService.getApiService.getTopHeadlinesNews(
            apiKey,
            country,
            category,
            sources,
            q
        )
        return if (response.status == "ok"){
            val data = response.articleEntities.map { mapper.mapFromRemote(it) }
            com.anangkur.beritaku.core.base.BaseResult.success(data)
        }else{
            com.anangkur.beritaku.core.base.BaseResult.error(response.message?:"")
        }
    }

    companion object{
        private var INSTANCE: RemoteRepository? = null
        fun getInstance() = INSTANCE
            ?: RemoteRepository(ArticleMapper())
    }
}