package com.anangkur.beritaku.app.feature.home

import android.view.View
import com.anangkur.beritaku.R
import com.anangkur.beritaku.app.base.BaseAdapter
import com.anangkur.beritaku.app.data.model.news.Article
import com.anangkur.beritaku.app.util.setImageUrl
import kotlinx.android.synthetic.main.item_breaking.view.*

class BreakingAdapter(private val listener: HomeActionListener): BaseAdapter<Article>(){

    override val layout: Int
        get() = R.layout.item_breaking

    override fun bind(data: Article, itemView: View, position: Int) {
        itemView.iv_item_breaking.setImageUrl(data.urlToImage?:"")
        itemView.tv_item_breaking.text = data.title
        itemView.setOnClickListener { listener.onClickItem(data) }
    }

}