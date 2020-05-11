package com.anangkur.beritaku.news

import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.anangkur.beritaku.base.BaseActivity
import com.anangkur.beritaku.obtainViewModel
import com.anangkur.beritaku.presentation.features.news.NewsViewModel
import com.anangkur.beritaku.R as appR

class NewsActivity: BaseActivity<NewsViewModel>() {

    override val mLayout: Int
        get() = R.layout.activity_news
    override val mViewModel: NewsViewModel
        get() = obtainViewModel(NewsViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = findViewById(appR.id.toolbar)
}