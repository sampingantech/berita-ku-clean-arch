package beritaku.features.news.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import beritaku.feature.news.R
import beritaku.features.news.mapper.ArticleMapper
import beritaku.features.news.model.ArticleIntent
import beritaku.features.news.obtainViewModel
import beritaku.features.news.originalNews.OriginalNewsActivity
import com.anangkur.beritaku.core.base.BaseActivity
import com.anangkur.beritaku.core.util.setImageUrl
import com.anangkur.beritaku.presentation.features.news.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class DetailActivity: BaseActivity<DetailViewModel>(), DetailActivityActionListener {

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
        get() = toolbar
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
