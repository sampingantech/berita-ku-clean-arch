package com.anangkur.beritaku.app.data.remote

import com.anangkur.beritaku.app.base.BaseDataSource
import com.anangkur.beritaku.app.data.DataSource
import com.anangkur.beritaku.app.data.model.BaseResult
import com.anangkur.beritaku.app.data.model.news.GetNewsResponse

class RemoteRepository: DataSource, BaseDataSource() {

    override suspend fun getTopHeadlinesNews(
        apiKey: String?,
        country: String?,
        category: String?,
        sources: String?,
        q: String?
    ): BaseResult<GetNewsResponse> {
        return getResult {
            ApiService.getApiService.getTopHeadlinesNews(
                apiKey,
                country,
                category,
                sources,
                q
            )
        }
    }

    companion object{
        private var INSTANCE: RemoteRepository? = null
        fun getInstance() = INSTANCE
            ?: RemoteRepository()
    }
}