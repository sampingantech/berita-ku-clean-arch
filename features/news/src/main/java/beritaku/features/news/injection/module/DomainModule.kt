package beritaku.features.news.injection.module

import com.anangkur.beritaku.domain.features.news.GetArticles
import com.anangkur.beritaku.domain.repository.ArticleRepository
import dagger.Module
import dagger.Provides

@Module
class DomainModule{

    @Provides
    fun provideGetArticle(): GetArticles{
        return GetArticles()
    }
}