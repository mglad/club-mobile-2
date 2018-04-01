package io.mglad.clubmobile.ui.dashboard

import android.support.annotation.StringRes
import io.mglad.clubmobile.base.navigation.BaseNavigationView
import io.mglad.clubmobile.model.Club

interface DashboardView : BaseNavigationView {
    fun showError(error: String)

    fun showError(@StringRes errorResId: Int){
        this.showError(getContext().getString(errorResId))
    }

    fun updateClubs(clubs: List<Club>)

    fun showLoading()

    fun hideLoading()

    fun goToClub(club: Club)
}