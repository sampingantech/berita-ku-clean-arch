package com.anangkur.beritaku.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel

abstract class BaseActivity<T: ViewModel?>: AppCompatActivity(){

    @get:LayoutRes
    abstract val mLayout: Int
    abstract val mViewModel: T?
    abstract val mToolbar: Toolbar?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mLayout)

        setupToolbar(mToolbar)
    }

    private fun setupToolbar(toolbar: Toolbar?){
        toolbar?.let { setSupportActionBar(it) }
    }
}