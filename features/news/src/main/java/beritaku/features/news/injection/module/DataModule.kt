package beritaku.features.news.injection.module

import com.anangkur.beritaku.data.ArticleDataRepository
import com.anangkur.beritaku.data.mapper.ArticleMapper
import com.anangkur.beritaku.data.repository.ArticleLocal
import com.anangkur.beritaku.data.repository.ArticleRemote
import com.anangkur.beritaku.data.source.ArticleDataStoreFactory
import com.anangkur.beritaku.data.source.ArticleLocalDataStore
import com.anangkur.beritaku.data.source.ArticleRemoteDataStore
import com.anangkur.beritaku.domain.repository.ArticleRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideArticleMapper(): ArticleMapper {
        return ArticleMapper()
    }

    @Provides
    fun provideArticleDataStoreFactory(
        articleLocalDataStore: ArticleLocalDataStore,
        articleRemoteDataStore: ArticleRemoteDataStore
    ): ArticleDataStoreFactory{
        return ArticleDataStoreFactory(articleLocalDataStore, articleRemoteDataStore)
    }

    @Provides
    fun provideArticleLocalDataStore(
        articleLocal: ArticleLocal
    ): ArticleLocalDataStore{
        return ArticleLocalDataStore(articleLocal)
    }

    @Provides
    fun provideArticleRemoteDataStore(
        articleRemote: ArticleRemote
    ): ArticleRemoteDataStore{
        return ArticleRemoteDataStore(articleRemote)
    }

    @Provides
    fun provideArticleRepository(
        articleDataStoreFactory: ArticleDataStoreFactory,
        articleMapper: ArticleMapper
    ): ArticleRepository {
        return ArticleDataRepository(articleDataStoreFactory, articleMapper)
    }
}