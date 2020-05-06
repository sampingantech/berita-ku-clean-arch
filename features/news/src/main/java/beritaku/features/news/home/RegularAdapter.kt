package beritaku.features.news.home

import android.view.View
import beritaku.feature.news.R
import com.anangkur.beritaku.core.base.BaseAdapter
import com.anangkur.beritaku.core.util.setImageUrl
import com.anangkur.beritaku.data.model.news.Article
import kotlinx.android.synthetic.main.item_regular.view.*

class RegularAdapter(private val listener: HomeActionListener): BaseAdapter<Article>() {
    override val layout: Int
        get() = R.layout.item_regular

    override fun bind(data: Article, itemView: View, position: Int) {
        itemView.iv_item_regular.setImageUrl(data.urlToImage?:"")
        itemView.tv_item_regular.text = data.title
        itemView.setOnClickListener { listener.onClickItem(data) }
    }
}