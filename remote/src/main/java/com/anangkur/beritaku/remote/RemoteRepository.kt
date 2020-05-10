package com.anangkur.beritaku.remote

import com.anangkur.beritaku.core.base.BaseResult
import com.anangkur.beritaku.data.model.ArticleEntity
import com.anangkur.beritaku.data.repository.ArticleRemote
import com.anangkur.beritaku.remote.mapper.ArticleMapper

class RemoteRepository (
    private val mapper: ArticleMapper,
    private val service: ApiService
): ArticleRemote, BaseDataSource() {

    companion object{
        private var INSTANCE: RemoteRepository? = null
        fun getInstance() = INSTANCE ?: RemoteRepository(
            ArticleMapper.getInstance(),
            ApiService.getApiService
        )
    }

    override suspend fun getTopHeadlinesNews(
        apiKey: String?,
        country: String?,
        category: String?,
        sources: String?,
        q: String?
    ): BaseResult<List<ArticleEntity>> {
        val response = service.getTopHeadlinesNews(
            apiKey,
            country,
            category,
            sources,
            q
        )
        return if (response.status == "ok"){
            val data = response.articleEntities.map { mapper.mapFromRemote(it) }
            BaseResult.success(data)
        }else{
            BaseResult.error(response.message?:"")
        }
    }
}