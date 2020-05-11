package com.anangkur.beritaku.news.home

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.anangkur.beritaku.mapper.ArticleMapper
import com.anangkur.beritaku.model.ArticleIntent
import com.anangkur.beritaku.*
import com.anangkur.beritaku.base.BaseActivity
import com.anangkur.beritaku.core.BaseResult
import com.anangkur.beritaku.presentation.features.news.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import com.anangkur.beritaku.news.R
import com.anangkur.beritaku.R as appR

class HomeActivity: BaseActivity<HomeViewModel>(), HomeActionListener {

    override val mLayout: Int
        get() = R.layout.activity_home
    override val mViewModel: HomeViewModel
        get() = obtainViewModel(HomeViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = findViewById(appR.id.toolbar)
    override val mTitleToolbar: String?
        get() = getString(appR.string.app_name)

    private lateinit var adapterBreaking: BreakingAdapter
    private lateinit var adapterBusiness: RegularAdapter
    private lateinit var adapterTech: RegularAdapter
    private lateinit var adapterSport: RegularAdapter

    private var mapper = ArticleMapper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupAdapterBreaking()
        setupAdapterBusiness()
        setupAdapterTech()
        setupAdapterSport()
        observeViewModel()
    }

    private fun observeViewModel(){
        mViewModel.apply {
            topHeadlineNewsLive.observe(this@HomeActivity, Observer {
                when (it.status){
                    BaseResult.Status.LOADING -> {
                        if (it.isLoading!!){
                            pb_breaking.visible()
                        }else{
                            pb_breaking.gone()
                        }
                    }
                    BaseResult.Status.ERROR -> {
                        showSnackbarShort(it.message?:"")
                    }
                    BaseResult.Status.SUCCESS -> {
                         separateMoviesBreaking(mapToView(it.data!!))
                    }
                }
            })
            businessNewsLive.observe(this@HomeActivity, Observer {
                when (it.status){
                    BaseResult.Status.LOADING -> {
                        if (it.isLoading!!){
                            pb_business.visible()
                        }else{
                            pb_business.gone()
                        }
                    }
                    BaseResult.Status.ERROR -> {
                        showSnackbarShort(it.message?:"")
                    }
                    BaseResult.Status.SUCCESS -> {
                         it.data?.let {listArticle ->
                             adapterBusiness.setRecyclerData(mapToView(listArticle).map { articleView ->
                                 this@HomeActivity.mapper.mapToIntent(articleView)
                             })
                         }
                    }
                }
            })
            techNewsLive.observe(this@HomeActivity, Observer {
                when (it.status){
                    BaseResult.Status.LOADING -> {
                        if (it.isLoading!!){
                            pb_tech.visible()
                        }else{
                            pb_tech.gone()
                        }
                    }
                    BaseResult.Status.ERROR -> {
                        showSnackbarShort(it.message?:"")
                    }
                    BaseResult.Status.SUCCESS -> {
                        it.data?.let {listArticle ->
                            adapterTech.setRecyclerData(mapToView(listArticle).map { articleView ->
                                this@HomeActivity.mapper.mapToIntent(articleView)
                            })
                        }
                    }
                }
            })
            sportNewsLive.observe(this@HomeActivity, Observer {
                when (it.status){
                    BaseResult.Status.LOADING -> {
                        if (it.isLoading!!){
                            pb_sport.visible()
                        }else{
                            pb_sport.gone()
                        }
                    }
                    BaseResult.Status.ERROR -> {
                        showSnackbarShort(it.message?:"")
                    }
                    BaseResult.Status.SUCCESS -> {
                        it.data?.let {listArticle ->
                            adapterSport.setRecyclerData(mapToView(listArticle).map { articleView ->
                                this@HomeActivity.mapper.mapToIntent(articleView)
                            })
                        }
                    }
                }
            })
            firstTopHeadlineLive.observe(this@HomeActivity, Observer {
                setupFirstBreaking(this@HomeActivity.mapper.mapToIntent(it))
            })
            topHeadlineLive.observe(this@HomeActivity, Observer {list ->
                adapterBreaking.setRecyclerData(list.map { this@HomeActivity.mapper.mapToIntent(it) })
            })
        }
    }

    private fun setupAdapterBreaking(){
        adapterBreaking = BreakingAdapter(this)
        recycler_breaking.apply {
            adapter = adapterBreaking
            setupRecyclerViewLinear(this@HomeActivity, LinearLayout.VERTICAL)
        }
    }

    private fun setupAdapterBusiness(){
        adapterBusiness = RegularAdapter(this)
        recycler_business.apply {
            adapter = adapterBusiness
            setupRecyclerViewLinear(this@HomeActivity, LinearLayout.HORIZONTAL)
        }
    }

    private fun setupAdapterTech(){
        adapterTech = RegularAdapter(this)
        recycler_tech.apply {
            adapter = adapterTech
            setupRecyclerViewLinear(this@HomeActivity, LinearLayout.HORIZONTAL)
        }
    }

    private fun setupAdapterSport(){
        adapterSport = RegularAdapter(this)
        recycler_sport.apply {
            adapter = adapterSport
            setupRecyclerViewLinear(this@HomeActivity, LinearLayout.HORIZONTAL)
        }
    }

    private fun setupFirstBreaking(data: ArticleIntent){
        tv_title_breaking.text = data.title
        tv_content_breaking.text = data.content
        iv_breaking.setImageUrl(data.urlToImage?:"")
        btn_read_more_breaking.setOnClickListener { this.onClickItem(data) }
    }

    override fun onClickItem(data: ArticleIntent) {
        com.anangkur.beritaku.news.detail.DetailActivity.startActivity(this, data)
    }
}
