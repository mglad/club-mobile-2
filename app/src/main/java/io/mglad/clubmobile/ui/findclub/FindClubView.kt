package io.mglad.clubmobile.ui.findclub

import android.support.annotation.StringRes
import io.mglad.clubmobile.base.navigation.BaseNavigationView
import io.mglad.clubmobile.model.Club

interface FindClubView: BaseNavigationView {
    fun showError(error: String)

    fun showError(@StringRes errorResId: Int){
        this.showError(getContext().getString(errorResId))
    }

    fun updateClubs(clubs: List<Club>)

    fun showLoading()

    fun hideLoading()

    fun showJoinSuccess(name: String)

    fun showJoinAlert(club: Club)
}