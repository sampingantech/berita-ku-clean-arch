package com.anangkur.beritaku.feature.home

import com.anangkur.beritaku.model.ArticleIntent

interface HomeActionListener {
    fun onClickItem(data: ArticleIntent)
}