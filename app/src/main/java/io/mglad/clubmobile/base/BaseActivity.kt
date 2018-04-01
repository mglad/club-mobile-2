package io.mglad.clubmobile.base

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.mglad.clubmobile.R

abstract class BaseActivity<P : BasePresenter<BaseView>, B : ViewDataBinding> : BaseView, AppCompatActivity() {
    protected lateinit var presenter: P
    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
        binding = instantiateBinding()
    }

    protected abstract fun layoutId(): Int

    /**
     * Instantiates the presenter the Activity is based on.
     */
    protected abstract fun instantiatePresenter(): P

    private fun instantiateBinding(): B  = DataBindingUtil.setContentView(this, layoutId())

    override fun getContext(): Context = this
}