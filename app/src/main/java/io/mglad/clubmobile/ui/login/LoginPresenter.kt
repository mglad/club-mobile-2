package io.mglad.clubmobile.ui.login

import io.mglad.clubmobile.R
import io.mglad.clubmobile.UserManager
import io.mglad.clubmobile.base.BasePresenter
import io.mglad.clubmobile.model.LoginRequest
import io.mglad.clubmobile.model.LoginResponse
import io.mglad.clubmobile.network.ClubApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginPresenter(loginView: LoginView) : BasePresenter<LoginView>(loginView) {
    private var subscription: Disposable? = null

    override fun onViewCreated() {
        view.hideLoading()

        login("clubuser", "pass")
    }

    fun login(username: String, password: String) {
        if (!validate(username, password)) return

        view.showLoading()
        subscription = clubApi
                .login(LoginRequest(username, password))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { loginResponse -> loginSuccess(loginResponse)},
                        { view.showError(R.string.unknown_error) }
                )
    }

    private fun validate(username: String, password: String): Boolean {
        var validated = true
        if(username.isBlank()) {
            validated = false
            view.showUsernameError("Username cannot be empty")
        }

        if(password.isBlank()) {
            validated = false
            view.showPasswordError("Password cannot be empty")
        }

        return validated
    }

    private fun loginSuccess(loginResponse: LoginResponse) {
        userManager.setSessionId(loginResponse.sessionId)
        userManager.setUser(loginResponse.user)

        view.navigateToDashboard()
    }

    override fun onViewDestroyed() {
        subscription?.dispose()
    }

    fun register() {
        view.goToRegistration()
    }

    fun registrationSuccess() {
        view.showRegistrationSuccess()
    }
}