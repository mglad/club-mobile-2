package io.mglad.clubmobile.base.toolbar

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.MenuItem
import io.mglad.clubmobile.base.BaseActivity
import io.mglad.clubmobile.base.BasePresenter
import io.mglad.clubmobile.base.BaseView
import kotlinx.android.synthetic.main.app_bar_main.*



abstract class BaseToolbarActivity<P : BasePresenter<BaseView>, B : ViewDataBinding>  : BaseActivity<P, B>() {
    abstract fun title(): String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar.title = title()

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.itemId == android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item)
    }
}