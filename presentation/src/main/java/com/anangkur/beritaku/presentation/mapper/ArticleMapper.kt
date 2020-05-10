package com.anangkur.beritaku.presentation.mapper

import com.anangkur.beritaku.domain.model.Article
import com.anangkur.beritaku.presentation.model.ArticleView
import javax.inject.Inject

class ArticleMapper @Inject constructor(): Mapper<ArticleView, Article> {
    override fun mapToView(type: Article): ArticleView {
        return ArticleView(
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