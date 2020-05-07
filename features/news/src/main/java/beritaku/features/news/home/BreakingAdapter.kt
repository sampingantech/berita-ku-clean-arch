package beritaku.features.news.home

import android.view.View
import beritaku.feature.news.R
import beritaku.features.news.model.ArticleIntent
import com.anangkur.beritaku.core.base.BaseAdapter
import com.anangkur.beritaku.core.util.setImageUrl
import kotlinx.android.synthetic.main.item_breaking.view.*

class BreakingAdapter(private val listener: HomeActionListener): BaseAdapter<ArticleIntent>(){

    override val layout: Int
        get() = R.layout.item_breaking

    override fun bind(data: ArticleIntent, itemView: View, position: Int) {
        itemView.iv_item_breaking.setImageUrl(data.urlToImage?:"")
        itemView.tv_item_breaking.text = data.title
        itemView.setOnClickListener { listener.onClickItem(data) }
    }

}