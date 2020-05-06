package com.anangkur.beritaku.data.remote

class RemoteRepository: com.anangkur.beritaku.data.DataSource, com.anangkur.beritaku.data.BaseDataSource() {

    override suspend fun getTopHeadlinesNews(
        apiKey: String?,
        country: String?,
        category: String?,
        sources: String?,
        q: String?
    ): com.anangkur.beritaku.data.model.BaseResult<com.anangkur.beritaku.data.model.news.GetNewsResponse> {
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