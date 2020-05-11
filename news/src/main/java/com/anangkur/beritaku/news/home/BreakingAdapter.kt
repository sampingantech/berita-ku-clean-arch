package com.anangkur.beritaku.news.home

import android.view.View
import com.anangkur.beritaku.model.ArticleIntent
import com.anangkur.beritaku.base.BaseAdapter
import com.anangkur.beritaku.news.R
import com.anangkur.beritaku.setImageUrl
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