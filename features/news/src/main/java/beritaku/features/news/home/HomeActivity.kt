package beritaku.features.news.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import beritaku.feature.news.R
import beritaku.features.news.ViewModelFactory.Companion.obtainViewModel
import beritaku.features.news.detail.DetailActivity
import com.anangkur.beritaku.core.base.BaseActivity
import com.anangkur.beritaku.core.util.*
import com.anangkur.beritaku.core.base.BaseResult
import com.anangkur.beritaku.data.model.ArticleEntity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class HomeActivity: BaseActivity<HomeViewModel>(),
    HomeActionListener {

    companion object{
        fun startActivity(context: Context){
            context.startActivity(Intent(context, HomeActivity::class.java))
        }
    }

    override val mLayout: Int
        get() = R.layout.activity_home
    override val mViewModel: HomeViewModel
        get() = obtainViewModel(HomeViewModel::class.java)
    override val mToolbar: Toolbar?
        get() = toolbar
    override val mTitleToolbar: String?
        get() = getString(R.string.app_name)

    private lateinit var adapterBreaking: BreakingAdapter
    private lateinit var adapterBusiness: RegularAdapter
    private lateinit var adapterTech: RegularAdapter
    private lateinit var adapterSport: RegularAdapter

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
                        separateMoviesBreaking(it.data)
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
                        if (it.data != null) adapterBusiness.setRecyclerData(it.data!!)
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
                        if (it.data != null) adapterTech.setRecyclerData(it.data!!)
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
                        if (it.data != null) adapterSport.setRecyclerData(it.data!!)
                    }
                }
            })
            firstTopHeadlineLive.observe(this@HomeActivity, Observer {
                setupFirstBreaking(it)
            })
            topHeadlineLive.observe(this@HomeActivity, Observer {
                adapterBreaking.setRecyclerData(it)
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

    private fun setupFirstBreaking(data: ArticleEntity){
        tv_title_breaking.text = data.title
        tv_content_breaking.text = data.content
        iv_breaking.setImageUrl(data.urlToImage?:"")
        btn_read_more_breaking.setOnClickListener { this.onClickItem(data) }
    }

    override fun onClickItem(data: ArticleEntity) {
        DetailActivity.startActivity(this, data)
    }
}
