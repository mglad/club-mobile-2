package io.mglad.clubmobile.ui.club.fragment.twitter

import android.support.annotation.StringRes
import io.mglad.clubmobile.base.BaseView

interface ClubTwitterView: BaseView{
    fun showLoading()

    fun hideLoading()

    fun showError(error: String)

    fun showError(@StringRes errorResId: Int){
        this.showError(getContext().getString(errorResId))
    }

    fun updateTweets(tweets: List<String>?)
}