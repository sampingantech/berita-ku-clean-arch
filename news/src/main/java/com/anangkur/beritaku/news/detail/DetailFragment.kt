package com.anangkur.beritaku.news.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.anangkur.beritaku.base.BaseFragment
import com.anangkur.beritaku.mapper.ArticleMapper
import com.anangkur.beritaku.model.ArticleIntent
import com.anangkur.beritaku.news.NewsActivity
import com.anangkur.beritaku.news.R
import com.anangkur.beritaku.R as appR
import com.anangkur.beritaku.presentation.features.news.NewsViewModel
import com.anangkur.beritaku.setImageUrl
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment: BaseFragment<NewsViewModel>(), DetailActivityActionListener {

    override val mLayout: Int
        get() = R.layout.fragment_detail
    override val mViewModel: NewsViewModel
        get() = (requireActivity() as NewsActivity).mViewModel
    override val mToolbar: Toolbar?
        get() = (requireActivity() as NewsActivity).mToolbar

    private val mapper = ArticleMapper()

    private var articleIntent: ArticleIntent? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getIntentData()
        setupDataToView(articleIntent)
        btn_read_more.setOnClickListener { this.onClickSeeOriginal(articleIntent?.url?:"") }
    }

    private fun getIntentData(){
        articleIntent = mViewModel.selectedNews?.let { mapper.mapToIntent(it) }
    }

    private fun setupDataToView(data: ArticleIntent?){
        tv_title_detail.text = data?.title
        tv_content_detail.text = data?.content
        iv_detail.setImageUrl(data?.urlToImage?:"")
    }

    override fun onClickSeeOriginal(url: String) {
        mViewModel.originalNewsUrl = url
        findNavController().navigate(R.id.action_detail_fragment_to_original_news_fragment)
    }

    override fun setupToolbar(toolbar: Toolbar?) {
        toolbar?.title = mViewModel.selectedNews?.title
        toolbar?.navigationIcon = ContextCompat.getDrawable(requireContext(), appR.drawable.ic_arrow_back_black_24dp)
        toolbar?.setNavigationOnClickListener { requireActivity().onBackPressed() }
    }
}