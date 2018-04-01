package io.mglad.clubmobile.ui.registration

import android.support.annotation.StringRes
import io.mglad.clubmobile.base.BaseView

interface RegistrationView : BaseView {
    fun showError(error: String)

    fun showError(@StringRes errorResId: Int){
        this.showError(getContext().getString(errorResId))
    }

    fun showLoading()

    fun hideLoading()

    fun showUsernameError(error: String)

    fun showPasswordError(error: String)

    fun showFirstNameError(error: String)

    fun showLastNameError(error: String)

    fun stop()
}