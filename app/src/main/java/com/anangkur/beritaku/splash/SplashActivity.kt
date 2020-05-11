package com.anangkur.beritaku.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import com.anangkur.beritaku.BuildConfig
import com.anangkur.beritaku.R
import com.anangkur.beritaku.base.BaseActivity

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
            val intent = Intent()
            intent.setClassName(BuildConfig.APPLICATION_ID, "com.anangkur.beritaku.news.home.HomeActivity")
            startActivity(intent)
            finish()
        }, 3000)
    }

}
