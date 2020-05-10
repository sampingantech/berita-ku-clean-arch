package com.anangkur.beritaku.feature.home

import android.view.View
import com.anangkur.beritaku.model.ArticleIntent
import com.anangkur.beritaku.R
import com.anangkur.beritaku.base.BaseAdapter
import com.anangkur.beritaku.setImageUrl
import kotlinx.android.synthetic.main.item_regular.view.*

class RegularAdapter(private val listener: HomeActionListener): BaseAdapter<ArticleIntent>() {
    override val layout: Int
        get() = R.layout.item_regular

    override fun bind(data: ArticleIntent, itemView: View, position: Int) {
        itemView.iv_item_regular.setImageUrl(data.urlToImage?:"")
        itemView.tv_item_regular.text = data.title
        itemView.setOnClickListener { listener.onClickItem(data) }
    }
}