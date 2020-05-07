package beritaku.features.news.injection.module

import com.anangkur.beritaku.data.repository.ArticleRemote
import com.anangkur.beritaku.remote.ApiService
import com.anangkur.beritaku.remote.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object{
        @Provides
        @JvmStatic
        fun provideArticleService(): ApiService {
            return ApiService.getApiService
        }
    }

    @Binds
    abstract fun bindRemoteRepository(remoteRepository: RemoteRepository): ArticleRemote
}