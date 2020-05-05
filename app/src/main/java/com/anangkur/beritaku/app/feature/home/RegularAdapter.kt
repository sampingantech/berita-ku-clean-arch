package com.anangkur.beritaku.app.feature.home

import android.view.View
import com.anangkur.beritaku.R
import com.anangkur.beritaku.app.base.BaseAdapter
import com.anangkur.beritaku.app.data.model.news.Article
import com.anangkur.beritaku.app.util.setImageUrl
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