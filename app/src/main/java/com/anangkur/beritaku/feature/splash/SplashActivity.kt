package com.anangkur.beritaku.feature.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import beritaku.features.news.home.HomeActivity
import com.anangkur.beritaku.R
import com.anangkur.beritaku.core.base.BaseActivity

class SplashActivity: BaseActivity<ViewModel>(){

    override val mLayout: Int
        get() = R.layout.activity_splash
    override val mViewModel: ViewModel?
        get() = null
    override val mToolbar: Toolbar?
        get() = null
    override val mTitleToolbar: String?
        get() = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        openActivity()
    }

    private fun openActivity(){
        val handler = Handler()
        handler.postDelayed({
            HomeActivity.startActivity(this)
            finish()
        }, 3000)
    }

}
