package io.mglad.clubmobile.base

import io.mglad.clubmobile.UserManager
import io.mglad.clubmobile.injection.component.DaggerPresenterInjector
import io.mglad.clubmobile.injection.component.PresenterInjector
import io.mglad.clubmobile.injection.module.ContextModule
import io.mglad.clubmobile.injection.module.NetworkModule
import io.mglad.clubmobile.network.ClubApi
import io.mglad.clubmobile.ui.club.ClubPresenter
import io.mglad.clubmobile.ui.club.fragment.detail.ClubDetailPresenter
import io.mglad.clubmobile.ui.club.fragment.twitter.ClubTwitterAdapter
import io.mglad.clubmobile.ui.club.fragment.twitter.ClubTwitterPresenter
import io.mglad.clubmobile.ui.dashboard.DashboardPresenter
import io.mglad.clubmobile.ui.findclub.FindClubPresenter
import io.mglad.clubmobile.ui.login.LoginPresenter
import io.mglad.clubmobile.ui.registration.RegistrationPresenter
import javax.inject.Inject

abstract class BasePresenter<out V : BaseView>(protected val view: V) {
    @Inject
    lateinit var userManager: UserManager

    @Inject
    lateinit var clubApi: ClubApi

    private val injector: PresenterInjector = DaggerPresenterInjector
            .builder()
            .baseView(view)
            .contextModule(ContextModule)
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * This method may be called when the presenter view is created
     */
    open fun onViewCreated(){}

    /**
     * This method may be called when the presenter view is destroyed
     */
    open fun onViewDestroyed(){}

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is LoginPresenter -> injector.inject(this)
            is RegistrationPresenter -> injector.inject(this)
            is DashboardPresenter -> injector.inject(this)
            is FindClubPresenter -> injector.inject(this)
            is ClubPresenter -> injector.inject(this)
            is ClubDetailPresenter -> injector.inject(this)
            is ClubTwitterPresenter -> injector.inject(this)
        }
    }
}