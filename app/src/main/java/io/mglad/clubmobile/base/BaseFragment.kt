package io.mglad.clubmobile.base

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment<P : BasePresenter<BaseView>, B : ViewDataBinding> : Fragment() {
    protected var binding: B? = null
    protected lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantiatePresenter()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        presenter = instantiatePresenter()
        return binding!!.root
    }

    protected abstract fun layoutId(): Int

    protected abstract fun instantiatePresenter(): P

    override fun getContext(): Context = activity!!.applicationContext
}