package io.mglad.clubmobile.ui.club

import android.support.annotation.StringRes
import io.mglad.clubmobile.base.BaseView

interface ClubView: BaseView {
    fun showLeaveClubAlert()

    fun showLoading()

    fun hideLoading()

    fun showError(error: String)

    fun showError(@StringRes errorResId: Int){
        this.showError(getContext().getString(errorResId))
    }

    fun done()

    fun setupTabs()
}