package beritaku.features.news.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anangkur.beritaku.data.Repository
import com.anangkur.beritaku.data.model.ArticleEntity

class HomeViewModel(private val repository: Repository): ViewModel(){

    val topHeadlineNewsLive by lazy { repository.getTopHeadlinesNews() }
    val businessNewsLive by lazy { repository.getBusinessNews() }
    val techNewsLive by lazy { repository.getTechNews() }
    val sportNewsLive by lazy { repository.getSportNews() }

    val firstTopHeadlineLive = MutableLiveData<ArticleEntity>()
    val topHeadlineLive = MutableLiveData<List<ArticleEntity>>()
    fun separateMoviesBreaking(listNews: List<ArticleEntity>?){
        if (!listNews.isNullOrEmpty()){
            val tempListData = ArrayList<ArticleEntity>()
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