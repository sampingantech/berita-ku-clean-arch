package beritaku.features.news.mapper

import beritaku.features.news.model.ArticleIntent
import com.anangkur.beritaku.presentation.model.ArticleView
import javax.inject.Inject

class ArticleMapper @Inject constructor(): Mapper<ArticleIntent, ArticleView> {
    override fun mapToIntent(type: ArticleView): ArticleIntent {
        return ArticleIntent(
            id = type.id,
            title = type.title,
            author = type.author,
            category = type.category,
            content = type.content,
            description = type.description,
            publishedAt = type.publishedAt,
            url = type.url,
            urlToImage = type.urlToImage
        )
    }
}