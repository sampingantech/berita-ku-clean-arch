package com.anangkur.beritaku.app.feature.detail

import androidx.lifecycle.ViewModel
import com.anangkur.beritaku.app.data.Repository
import com.anangkur.beritaku.app.data.model.news.Article

class DetailViewModel(private val repository: Repository): ViewModel(){
    var article: Article? = null
}