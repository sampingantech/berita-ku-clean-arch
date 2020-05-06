package beritaku.features.news

import android.content.SharedPreferences
import com.anangkur.beritaku.data.Repository
import com.anangkur.beritaku.local.LocalRepository
import com.anangkur.beritaku.local.dao.AppDao
import com.anangkur.beritaku.remote.RemoteRepository

object Injection {
    fun provideRepository(preferences: SharedPreferences, dao: AppDao) =
        Repository.getInstance(
            RemoteRepository.getInstance(),
            LocalRepository.getInstance(preferences, dao)
        )
}