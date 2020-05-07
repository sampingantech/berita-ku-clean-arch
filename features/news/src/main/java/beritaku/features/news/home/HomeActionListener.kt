package beritaku.features.news.home

import beritaku.features.news.model.ArticleIntent

interface HomeActionListener {
    fun onClickItem(data: ArticleIntent)
}