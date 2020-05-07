package com.anangkur.beritaku.presentation.features.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.beritaku.domain.features.news.GetArticles
import com.anangkur.beritaku.presentation.mapper.ArticleMapper
import com.anangkur.beritaku.presentation.model.ArticleView
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getArticles: GetArticles,
    val mapper: ArticleMapper
): ViewModel(){

    val topHeadlineNewsLive by lazy { getArticles.getTopHeadlinesNews() }
    val businessNewsLive by lazy { getArticles.getBusinessNews() }
    val techNewsLive by lazy { getArticles.getTechNews() }
    val sportNewsLive by lazy { getArticles.getSportNews() }

    val firstTopHeadlineLive = MutableLiveData<ArticleView>()
    val topHeadlineLive = MutableLiveData<List<ArticleView>>()
    fun separateMoviesBreaking(listNews: List<ArticleView>?){
        if (!listNews.isNullOrEmpty()){
            val tempListData = ArrayList<ArticleView>()
            for (i in listNews.indices){
                if (i == 0){
                    firstTopHeadlineLive.postValue(listNews[i])
                }else if (i <= 5){
                    tempListData.add(listNews[i])
                }
            }
            topHeadlineLive.postValue(tempListData)
        }
    }
}