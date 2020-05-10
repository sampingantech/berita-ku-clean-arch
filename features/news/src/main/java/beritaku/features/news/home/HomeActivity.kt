package beritaku.features.news.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import beritaku.feature.news.R
import beritaku.features.news.detail.DetailActivity
import beritaku.features.news.injection.DaggerApplicationComponent
import beritaku.features.news.mapper.ArticleMapper
import beritaku.features.news.model.ArticleIntent
import com.anangkur.beritaku.core.base.BaseActivity
import com.anangkur.beritaku.core.util.setImageUrl
import com.anangkur.beritaku.core.util.setupRecyclerViewLinear
import com.anangkur.beritaku.presentation.features.news.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import javax.inject.Inject

class HomeActivity: BaseActivity<HomeViewModel>(), HomeActionListener {

    companion object{
        fun startActivity(context: Context){
            context.startActivity(Intent(context, HomeActivity::class.java))
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val mLayout: Int
        get() = R.layout.activity_home
    override val mViewModel: HomeViewModel
        get() = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = toolbar
    override val mTitleToolbar: String?
        get() = getString(R.string.app_name)

    private lateinit var adapterBreaking: BreakingAdapter
    private lateinit var adapterBusiness: RegularAdapter
    private lateinit var adapterTech: RegularAdapter
    private lateinit var adapterSport: RegularAdapter

    @Inject
    lateinit var mapper: ArticleMapper

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
//            topHeadlineNewsLive.observe(this@HomeActivity, Observer {
//                when (it.status){
//                    BaseResult.Status.LOADING -> {
//                        if (it.isLoading!!){
//                            pb_breaking.visible()
//                        }else{
//                            pb_breaking.gone()
//                        }
//                    }
//                    BaseResult.Status.ERROR -> {
//                        showSnackbarShort(it.message?:"")
//                    }
//                    BaseResult.Status.SUCCESS -> {
//                         separateMoviesBreaking(it.data?.map { item -> mViewModel.mapper.mapToView(item) })
//                    }
//                }
//            })
//            businessNewsLive.observe(this@HomeActivity, Observer {
//                when (it.status){
//                    BaseResult.Status.LOADING -> {
//                        if (it.isLoading!!){
//                            pb_business.visible()
//                        }else{
//                            pb_business.gone()
//                        }
//                    }
//                    BaseResult.Status.ERROR -> {
//                        showSnackbarShort(it.message?:"")
//                    }
//                    BaseResult.Status.SUCCESS -> {
//                         val data = it.data!!.map { mapper.mapToView(it) }
//                         if (it.data != null) adapterBusiness.setRecyclerData(data.map { this@HomeActivity.mapper.mapToIntent(it) })
//                    }
//                }
//            })
//            techNewsLive.observe(this@HomeActivity, Observer {
//                when (it.status){
//                    BaseResult.Status.LOADING -> {
//                        if (it.isLoading!!){
//                            pb_tech.visible()
//                        }else{
//                            pb_tech.gone()
//                        }
//                    }
//                    BaseResult.Status.ERROR -> {
//                        showSnackbarShort(it.message?:"")
//                    }
//                    BaseResult.Status.SUCCESS -> {
//                         val data = it.data!!.map { mapper.mapToView(it) }
//                         if (it.data != null) adapterTech.setRecyclerData(data.map { this@HomeActivity.mapper.mapToIntent(it) })
//                    }
//                }
//            })
//            sportNewsLive.observe(this@HomeActivity, Observer {
//                when (it.status){
//                    BaseResult.Status.LOADING -> {
//                        if (it.isLoading!!){
//                            pb_sport.visible()
//                        }else{
//                            pb_sport.gone()
//                        }
//                    }
//                    BaseResult.Status.ERROR -> {
//                        showSnackbarShort(it.message?:"")
//                    }
//                    BaseResult.Status.SUCCESS -> {
//                         val data = it.data!!.map { mapper.mapToView(it) }
//                         if (it.data != null) adapterSport.setRecyclerData(data.map { this@HomeActivity.mapper.mapToIntent(it) })
//                    }
//                }
//            })
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
        DetailActivity.startActivity(this, data)
    }

    override fun onCreateInjector() {
        DaggerApplicationComponent
            .builder()
            .build()
            .inject(this)
    }
}
