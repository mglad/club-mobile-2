package io.mglad.clubmobile.ui.login

import android.support.annotation.StringRes
import io.mglad.clubmobile.base.BaseView

interface LoginView : BaseView {
    fun navigateToDashboard()

    fun showError(error: String)

    fun showError(@StringRes errorResId: Int){
        this.showError(getContext().getString(errorResId))
    }

    fun showLoading()

    fun hideLoading()

    fun showUsernameError(error: String)

    fun showPasswordError(error: String)

    fun goToRegistration()

    fun showRegistrationSuccess()
}