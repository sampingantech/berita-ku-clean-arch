package beritaku.features.news.home

import com.anangkur.beritaku.data.model.ArticleEntity

interface HomeActionListener {
    fun onClickItem(data: ArticleEntity)
}