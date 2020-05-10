package beritaku.features.news.injection.module

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.anangkur.beritaku.data.repository.ArticleLocal
import com.anangkur.beritaku.local.LocalRepository
import com.anangkur.beritaku.local.db.AppDatabase
import com.anangkur.beritaku.local.db.constant.Const
import com.anangkur.beritaku.local.mapper.ArticleMapper
import dagger.Module
import dagger.Provides

@Module
class LocalModule {

    @Provides
    fun provideArticleDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            Const.DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideArticleMapper(): ArticleMapper{
        return ArticleMapper()
    }

    @Provides
    fun providePreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    fun provideArticleLocal(
        preferences: SharedPreferences,
        appDatabase: AppDatabase,
        mapper: ArticleMapper
    ): ArticleLocal {
        return LocalRepository(preferences, mapper, appDatabase)
    }
}