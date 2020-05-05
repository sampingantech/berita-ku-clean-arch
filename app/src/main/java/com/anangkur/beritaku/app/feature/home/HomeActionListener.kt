package com.anangkur.beritaku.app.feature.home

import com.anangkur.beritaku.app.data.model.news.Article

interface HomeActionListener {
    fun onClickItem(data: Article)
}