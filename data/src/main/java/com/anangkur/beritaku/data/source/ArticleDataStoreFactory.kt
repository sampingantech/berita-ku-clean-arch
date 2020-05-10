package com.anangkur.beritaku.data.source

import com.anangkur.beritaku.data.repository.ArticleDataStore
import com.anangkur.beritaku.data.repository.ArticleLocal
import javax.inject.Inject

class ArticleDataStoreFactory (
    private val articleLocalDataStore: ArticleLocalDataStore,
    private val articleRemoteDataStore: ArticleRemoteDataStore
) {
    fun retrieveCacheDataStore(): ArticleDataStore {
        return articleLocalDataStore
    }

    fun retrieveRemoteDataStore(): ArticleDataStore {
        return articleRemoteDataStore
    }
}