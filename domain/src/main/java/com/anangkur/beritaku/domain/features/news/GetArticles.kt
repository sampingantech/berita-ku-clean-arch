package com.anangkur.beritaku.domain.features.news

import com.anangkur.beritaku.core.util.Const
import com.anangkur.beritaku.domain.repository.ArticleRepository
import com.anangkur.beritaku.domain.resultLiveData
import javax.inject.Inject

open class GetArticles @Inject constructor(/*val articleRepository: ArticleRepository*/) {

//    fun getTopHeadlinesNews() = resultLiveData(
//        databaseQuery = { articleRepository.getAllDataByCategory(Const.CATEGORY_GENERAL) },
//        networkCall = {
//            articleRepository.getTopHeadlinesNews(
//                Const.API_KEY,
//                Const.COUNTRY_ID,
//                Const.CATEGORY_GENERAL,
//                null,
//                null
//            )
//        },
//        saveCallResult = {
//            articleRepository.clearArticle(Const.CATEGORY_GENERAL)
//            articleRepository.saveArticles(it.map { article ->
//                article.apply { category = Const.CATEGORY_GENERAL }
//            })
//        }
//    )
//
//    fun getBusinessNews() = resultLiveData(
//        databaseQuery = { articleRepository.getAllDataByCategory(Const.CATEGORY_BUSSINESS) },
//        networkCall = {
//            articleRepository.getTopHeadlinesNews(
//                Const.API_KEY,
//                Const.COUNTRY_ID,
//                Const.CATEGORY_BUSSINESS,
//                null,
//                null
//            )
//        },
//        saveCallResult = {
//            articleRepository.clearArticle(Const.CATEGORY_BUSSINESS)
//            articleRepository.saveArticles(it.map { article ->
//                article.apply { category = Const.CATEGORY_BUSSINESS }
//            })
//        }
//    )
//
//    fun getTechNews() = resultLiveData(
//        databaseQuery = { articleRepository.getAllDataByCategory(Const.CATEGORY_TECHNOLOGY) },
//        networkCall = {
//            articleRepository.getTopHeadlinesNews(
//                Const.API_KEY,
//                Const.COUNTRY_ID,
//                Const.CATEGORY_TECHNOLOGY,
//                null,
//                null
//            )
//        },
//        saveCallResult = {
//            articleRepository.clearArticle(Const.CATEGORY_TECHNOLOGY)
//            articleRepository.saveArticles(it.map { article ->
//                article.apply { category = Const.CATEGORY_TECHNOLOGY }
//            })
//        }
//    )
//
//    fun getSportNews() = resultLiveData(
//        databaseQuery = { articleRepository.getAllDataByCategory(Const.CATEGOGY_SPORT) },
//        networkCall = {
//            articleRepository.getTopHeadlinesNews(
//                Const.API_KEY,
//                Const.COUNTRY_ID,
//                Const.CATEGOGY_SPORT,
//                null,
//                null
//            )
//        },
//        saveCallResult = {
//            articleRepository.clearArticle(Const.CATEGOGY_SPORT)
//            articleRepository.saveArticles(it.map { article ->
//                article.apply { category = Const.CATEGOGY_SPORT }
//            })
//        }
//    )
}