package beritaku.features.news.injection.module

import android.app.Application
import androidx.room.Room
import com.anangkur.beritaku.data.repository.ArticleLocal
import com.anangkur.beritaku.local.LocalRepository
import com.anangkur.beritaku.local.db.AppDatabase
import com.anangkur.beritaku.local.db.constant.Const
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class LocalModule {

    @Module
    companion object{
        @Provides
        @JvmStatic
        fun provideArticleDatabase(application: Application): AppDatabase {
            return Room.databaseBuilder(
                application.applicationContext,
                AppDatabase::class.java, Const.DATABASE_NAME
            ).build()
        }
    }

    @Binds
    abstract fun bindLocalRepository(localRepository: LocalRepository): ArticleLocal
}