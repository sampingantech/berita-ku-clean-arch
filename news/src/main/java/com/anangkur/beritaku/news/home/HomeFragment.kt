package com.anangkur.beritaku.news.home

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.anangkur.beritaku.*
import com.anangkur.beritaku.base.BaseFragment
import com.anangkur.beritaku.core.BaseResult
import com.anangkur.beritaku.mapper.ArticleMapper
import com.anangkur.beritaku.model.ArticleIntent
import com.anangkur.beritaku.news.NewsActivity
import com.anangkur.beritaku.news.R
import com.anangkur.beritaku.R as appR
import com.anangkur.beritaku.presentation.features.news.NewsViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: BaseFragment<NewsViewModel>(), HomeActionListener {

    override val mLayout: Int
        get() = R.layout.fragment_home
    override val mViewModel: NewsViewModel
        get() = (requireActivity() as NewsActivity).mViewModel
    override val mToolbar: Toolbar?
        get() = (requireActivity() as NewsActivity).mToolbar

    private lateinit var adapterBreaking: BreakingAdapter
    private lateinit var adapterBusiness: RegularAdapter
    private lateinit var adapterTech: RegularAdapter
    private lateinit var adapterSport: RegularAdapter

    private var mapper = ArticleMapper()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapterBreaking()
        setupAdapterBusiness()
        setupAdapterTech()
        setupAdapterSport()
        observeViewModel()
    }

    private fun observeViewModel(){
        mViewModel.apply {
            topHeadlineNewsLive.observe(viewLifecycleOwner, Observer {
                when (it.status){
                    BaseResult.Status.LOADING -> {
                        if (it.isLoading!!){
                            pb_breaking.visible()
                        }else{
                            pb_breaking.gone()
                        }
                    }
                    BaseResult.Status.ERROR -> {
                        requireActivity().showSnackbarShort(it.message?:"")
                    }
                    BaseResult.Status.SUCCESS -> {
                        separateMoviesBreaking(mapToView(it.data!!))
                    }
                }
            })
            businessNewsLive.observe(viewLifecycleOwner, Observer {
                when (it.status){
                    BaseResult.Status.LOADING -> {
                        if (it.isLoading!!){
                            pb_business.visible()
                        }else{
                            pb_business.gone()
                        }
                    }
                    BaseResult.Status.ERROR -> {
                        requireActivity().showSnackbarShort(it.message?:"")
                    }
                    BaseResult.Status.SUCCESS -> {
                        it.data?.let {listArticle ->
                            adapterBusiness.setRecyclerData(mapToView(listArticle).map { articleView ->
                                mapper.mapToIntent(articleView)
                            })
                        }
                    }
                }
            })
            techNewsLive.observe(viewLifecycleOwner, Observer {
                when (it.status){
                    BaseResult.Status.LOADING -> {
                        if (it.isLoading!!){
                            pb_tech.visible()
                        }else{
                            pb_tech.gone()
                        }
                    }
                    BaseResult.Status.ERROR -> {
                        requireActivity().showSnackbarShort(it.message?:"")
                    }
                    BaseResult.Status.SUCCESS -> {
                        it.data?.let {listArticle ->
                            adapterTech.setRecyclerData(mapToView(listArticle).map { articleView ->
                                mapper.mapToIntent(articleView)
                            })
                        }
                    }
                }
            })
            sportNewsLive.observe(viewLifecycleOwner, Observer {
                when (it.status){
                    BaseResult.Status.LOADING -> {
                        if (it.isLoading!!){
                            pb_sport.visible()
                        }else{
                            pb_sport.gone()
                        }
                    }
                    BaseResult.Status.ERROR -> {
                        requireActivity().showSnackbarShort(it.message?:"")
                    }
                    BaseResult.Status.SUCCESS -> {
                        it.data?.let {listArticle ->
                            adapterSport.setRecyclerData(mapToView(listArticle).map { articleView ->
                                mapper.mapToIntent(articleView)
                            })
                        }
                    }
                }
            })
            firstTopHeadlineLive.observe(viewLifecycleOwner, Observer {
                setupFirstBreaking(mapper.mapToIntent(it))
            })
            topHeadlineLive.observe(viewLifecycleOwner, Observer {list ->
                adapterBreaking.setRecyclerData(list.map { mapper.mapToIntent(it) })
            })
        }
    }

    private fun setupAdapterBreaking(){
        adapterBreaking = BreakingAdapter(this)
        recycler_breaking.apply {
            adapter = adapterBreaking
            setupRecyclerViewLinear(requireContext(), LinearLayout.VERTICAL)
        }
    }

    private fun setupAdapterBusiness(){
        adapterBusiness = RegularAdapter(this)
        recycler_business.apply {
            adapter = adapterBusiness
            setupRecyclerViewLinear(requireContext(), LinearLayout.HORIZONTAL)
        }
    }

    private fun setupAdapterTech(){
        adapterTech = RegularAdapter(this)
        recycler_tech.apply {
            adapter = adapterTech
            setupRecyclerViewLinear(requireContext(), LinearLayout.HORIZONTAL)
        }
    }

    private fun setupAdapterSport(){
        adapterSport = RegularAdapter(this)
        recycler_sport.apply {
            adapter = adapterSport
            setupRecyclerViewLinear(requireContext(), LinearLayout.HORIZONTAL)
        }
    }

    private fun setupFirstBreaking(data: ArticleIntent){
        tv_title_breaking.text = data.title
        tv_content_breaking.text = data.content
        iv_breaking.setImageUrl(data.urlToImage?:"")
        btn_read_more_breaking.setOnClickListener { this.onClickItem(data) }
    }

    override fun onClickItem(data: ArticleIntent) {
        mViewModel.selectedNews = mapper.mapFromIntent(data)
        findNavController().navigate(R.id.action_home_fragment_to_detail_fragment)
    }

    override fun setupToolbar(toolbar: Toolbar?) {
        toolbar?.title = getString(appR.string.app_name)
        toolbar?.navigationIcon = null
        toolbar?.setNavigationOnClickListener(null)
    }
}