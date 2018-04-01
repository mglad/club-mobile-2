package io.mglad.clubmobile.base.navigation

import io.mglad.clubmobile.base.BasePresenter

abstract class BaseNavigationPresenter<out V: BaseNavigationView>(navView: V) : BasePresenter<V>(navView) {
    override fun onViewCreated() {
        super.onViewCreated()

        view.setNavHeader(userManager.getUser()!!)
    }
    open fun selectedMyClubs() {
        view.goToMyClubs()
    }

    open fun selectedFindClubs() {
        view.goToFindClubs()
    }

    fun selectedSignOut() {
        userManager.clear()
        view.goToLogin()
    }
}