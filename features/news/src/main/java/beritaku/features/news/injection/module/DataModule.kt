package beritaku.features.news.injection.module

import com.anangkur.beritaku.data.ArticleDataRepository
import com.anangkur.beritaku.domain.repository.ArticleRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindArticleRepository(articleDataRepository: ArticleDataRepository): ArticleRepository
}