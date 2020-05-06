package beritaku.features.news.detail

import androidx.lifecycle.ViewModel
import com.anangkur.beritaku.data.Repository
import com.anangkur.beritaku.data.model.news.Article

class DetailViewModel(private val repository: Repository): ViewModel(){
    var article: Article? = null
}