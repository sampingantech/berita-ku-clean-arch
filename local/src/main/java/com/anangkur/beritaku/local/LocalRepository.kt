package com.anangkur.beritaku.local

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.anangkur.beritaku.core.util.Const
import com.anangkur.beritaku.data.DataSource
import com.anangkur.beritaku.data.model.ArticleEntity
import com.anangkur.beritaku.data.repository.ArticleLocal
import com.anangkur.beritaku.local.dao.AppDao
import com.anangkur.beritaku.local.mapper.ArticleMapper
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val preferences: SharedPreferences,
    private val mapper: ArticleMapper,
    private val dao: AppDao
): ArticleLocal {
    private val expirationTime = (60 * 10 * 1000).toLong()


    override suspend fun insertData(data: List<ArticleEntity>) { data.forEach { dao.insertData(mapper.mapToCached(it)) } }

    override suspend fun deleteByCategory(category: String) { dao.deleteByCategory(category) }

    override fun getAllDataByCategory(category: String): LiveData<List<ArticleEntity>> {
        return dao.getAllDataByCategory(category).map { list -> list.map { mapper.mapFromCached(it) } }
    }

    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > expirationTime
    }

    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferences.getLong(Const.PREF_CACHED_TIME, 0)
    }

    companion object{
        private var INSTANCE: LocalRepository? = null
        fun getInstance(
            preferences: SharedPreferences,
            dao: AppDao
        ) = INSTANCE ?: LocalRepository(preferences, ArticleMapper(), dao)
    }
}