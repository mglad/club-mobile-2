package io.mglad.clubmobile.ui.registration

import io.mglad.clubmobile.R
import io.mglad.clubmobile.base.BasePresenter
import io.mglad.clubmobile.model.RegistrationRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RegistrationPresenter(registrationView: RegistrationView) : BasePresenter<RegistrationView>(registrationView) {
    private var subscription: Disposable? = null

    override fun onViewCreated() {
        view.hideLoading()
    }

    fun register(username: String, password: String, firstName: String, lastName: String) {
        if (!validate(username, password, firstName, lastName)) return

        view.showLoading()
        subscription = clubApi
                .register(RegistrationRequest(username, password, firstName, lastName))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { _ -> view.stop() },
                        { view.showError(R.string.unknown_error) }
                )
    }

    private fun validate(username: String, password: String, firstName: String, lastName: String): Boolean {
        var validated = true

        if(username.isBlank()) {
            validated = false
            view.showUsernameError("Username cannot be empty")
        }

        if(password.isBlank()) {
            validated = false
            view.showPasswordError("Password cannot be empty")
        }

        if(firstName.isBlank()) {
            validated = false
            view.showFirstNameError("Username cannot be empty")
        }

        if(lastName.isBlank()) {
            validated = false
            view.showLastNameError("Password cannot be empty")
        }

        return validated
    }

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}