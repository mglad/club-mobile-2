package io.mglad.clubmobile.base.navigation

import io.mglad.clubmobile.base.BaseView
import io.mglad.clubmobile.model.User

interface BaseNavigationView : BaseView {
    fun goToMyClubs()
    fun goToFindClubs()
    fun goToLogin()
    fun setNavHeader(user: User)
}