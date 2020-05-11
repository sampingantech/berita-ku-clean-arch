package com.anangkur.beritaku.news.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.anangkur.beritaku.mapper.ArticleMapper
import com.anangkur.beritaku.model.ArticleIntent
import com.anangkur.beritaku.news.originalNews.OriginalNewsActivity
import com.anangkur.beritaku.base.BaseActivity
import com.anangkur.beritaku.news.R
import com.anangkur.beritaku.R as appR
import com.anangkur.beritaku.obtainViewModel
import com.anangkur.beritaku.presentation.features.news.DetailViewModel
import com.anangkur.beritaku.setImageUrl
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity: BaseActivity<DetailViewModel>(),
    DetailActivityActionListener {

    companion object{
        const val EXTRA_ARTICLE = "EXTRA_ARTICLE"
        fun startActivity(context: Context, data: ArticleIntent){
            context.startActivity(Intent(context, DetailActivity::class.java)
                .putExtra(EXTRA_ARTICLE, data))
        }
    }

    override val mLayout: Int
        get() = R.layout.activity_detail
    override val mViewModel: DetailViewModel
        get() = obtainViewModel(DetailViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = findViewById(appR.id.toolbar)
    override val mTitleToolbar: String?
        get() = null

    private val mapper = ArticleMapper()

    private lateinit var articleIntent: ArticleIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getIntentData()
        setupDataToView(articleIntent)
        btn_read_more.setOnClickListener { this.onClickSeeOriginal(articleIntent.url?:"") }
    }

    private fun getIntentData(){
        if (intent.hasExtra(EXTRA_ARTICLE)){
            articleIntent = intent.getParcelableExtra(EXTRA_ARTICLE)
        }
    }

    private fun setupDataToView(data: ArticleIntent?){
        tv_title_detail.text = data?.title
        tv_content_detail.text = data?.content
        iv_detail.setImageUrl(data?.urlToImage?:"")
    }

    override fun onClickSeeOriginal(url: String) {
        OriginalNewsActivity.startActivity(this, url)
    }
}
