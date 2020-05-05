package com.anangkur.beritaku.app.data

import android.content.SharedPreferences
import com.anangkur.beritaku.app.data.local.LocalRepository
import com.anangkur.beritaku.app.data.local.room.AppDao
import com.anangkur.beritaku.app.data.remote.RemoteRepository

object Injection {
    fun provideRepository(preferences: SharedPreferences, dao: AppDao) =
        Repository.getInstance(
            RemoteRepository.getInstance(),
            LocalRepository.getInstance(preferences, dao)
        )
}