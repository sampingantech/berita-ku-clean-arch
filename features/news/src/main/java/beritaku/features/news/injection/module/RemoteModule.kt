package beritaku.features.news.injection.module

import com.anangkur.beritaku.data.repository.ArticleRemote
import com.anangkur.beritaku.remote.ApiService
import com.anangkur.beritaku.remote.RemoteRepository
import com.anangkur.beritaku.remote.mapper.ArticleMapper
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class RemoteModule {

    @Provides
    fun providesApiService(): ApiService{
        return ApiService.getApiService
    }

    @Provides
    fun provideMapper(): ArticleMapper{
        return ArticleMapper()
    }

    @Provides
    fun provideArticleRemote(
        articleMapper: ArticleMapper,
        service: ApiService
    ): ArticleRemote{
        return RemoteRepository(articleMapper, service)
    }
}