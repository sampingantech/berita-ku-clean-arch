package beritaku.features.news.detail

import androidx.lifecycle.ViewModel
import com.anangkur.beritaku.data.Repository
import com.anangkur.beritaku.data.model.ArticleEntity

class DetailViewModel(private val repository: Repository): ViewModel(){
    var articleEntity: ArticleEntity? = null
}