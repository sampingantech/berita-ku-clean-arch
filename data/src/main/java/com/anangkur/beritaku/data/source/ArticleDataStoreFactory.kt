package com.anangkur.beritaku.data.source

import com.anangkur.beritaku.data.repository.ArticleDataStore
import com.anangkur.beritaku.data.repository.ArticleLocal
import javax.inject.Inject

class ArticleDataStoreFactory @Inject constructor(
    private val articleLocal: ArticleLocal,
    private val articleLocalDataStore: ArticleLocalDataStore,
    private val articleRemoteDataStore: ArticleRemoteDataStore
) {
    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(isCached: Boolean): ArticleDataStore {
        if (isCached && !articleLocal.isExpired()) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveCacheDataStore(): ArticleDataStore {
        return articleLocalDataStore
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveRemoteDataStore(): ArticleDataStore {
        return articleRemoteDataStore
    }
}