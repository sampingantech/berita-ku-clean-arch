package com.anangkur.beritaku.news.originalNews

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.anangkur.beritaku.base.BaseErrorView
import com.anangkur.beritaku.base.BaseFragment
import com.anangkur.beritaku.gone
import com.anangkur.beritaku.news.NewsActivity
import com.anangkur.beritaku.news.R
import com.anangkur.beritaku.presentation.features.news.NewsViewModel
import com.anangkur.beritaku.R as appR
import com.anangkur.beritaku.visible
import kotlinx.android.synthetic.main.fragment_original_news.*

class OriginalNewsFragment: BaseFragment<NewsViewModel>() {

    override val mLayout: Int
        get() = R.layout.fragment_original_news
    override val mViewModel: NewsViewModel
        get() = (requireActivity() as NewsActivity).mViewModel
    override val mToolbar: Toolbar?
        get() = (requireActivity() as NewsActivity).mToolbar

    private var isSuccessLoadUrl = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupWebView(mViewModel.originalNewsUrl)
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_original_news, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.menu_open_browser -> {
                val webPage = Uri.parse(mViewModel.originalNewsUrl)
                val intent = Intent(Intent.ACTION_VIEW, webPage)
                if (intent.resolveActivity(requireActivity().packageManager) != null) {
                    startActivity(intent)
                }
                true
            }
            else -> false
        }
    }

    private fun setupWebView(url: String){
        wv_original_news.loadUrl(url)
        wv_original_news.webViewClient = object: WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                isSuccessLoadUrl = true
                wv_original_news.gone()
                ev_original_news.showProgress()
                ev_original_news.visible()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (isSuccessLoadUrl){
                    wv_original_news.visible()
                    ev_original_news.gone()
                    ev_original_news.endProgress()
                }
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                isSuccessLoadUrl = false
                wv_original_news.gone()
                ev_original_news.showError(
                    errorMessage = getString(appR.string.error_default),
                    errorType = BaseErrorView.ERROR_GENERAL
                )
                ev_original_news.setRetryClickListener {
                    view?.reload()
                }
                ev_original_news.visible()
            }
        }
    }

    override fun setupToolbar(toolbar: Toolbar?) {
        toolbar?.title = mViewModel.selectedNews?.title
        toolbar?.navigationIcon = ContextCompat.getDrawable(requireContext(), appR.drawable.ic_arrow_back_black_24dp)
        toolbar?.setNavigationOnClickListener { requireActivity().onBackPressed() }
    }
}