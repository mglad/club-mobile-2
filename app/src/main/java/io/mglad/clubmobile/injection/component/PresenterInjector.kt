package io.mglad.clubmobile.injection.component

import dagger.BindsInstance
import dagger.Component
import io.mglad.clubmobile.base.BaseView
import io.mglad.clubmobile.injection.module.ContextModule
import io.mglad.clubmobile.injection.module.ManagerModule
import io.mglad.clubmobile.injection.module.NetworkModule
import io.mglad.clubmobile.ui.club.ClubPresenter
import io.mglad.clubmobile.ui.club.fragment.detail.ClubDetailPresenter
import io.mglad.clubmobile.ui.club.fragment.twitter.ClubTwitterPresenter
import io.mglad.clubmobile.ui.dashboard.DashboardPresenter
import io.mglad.clubmobile.ui.findclub.FindClubPresenter
import io.mglad.clubmobile.ui.login.LoginPresenter
import io.mglad.clubmobile.ui.registration.RegistrationPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class), (ManagerModule::class)])
interface PresenterInjector {
    /**
     * Injects required dependencies into the specified PostPresenter.
     * @param postPresenter PostPresenter in which to inject the dependencies
     */
    fun inject(loginPresenter: LoginPresenter)
    fun inject(registrationPresenter: RegistrationPresenter)
    fun inject(dashboardPresenter: DashboardPresenter)
    fun inject(findClubPresenter: FindClubPresenter)
    fun inject(clubPresenter: ClubPresenter)
    fun inject(clubDetailPresenter: ClubDetailPresenter)
    fun inject(clubTwitterPresenter: ClubTwitterPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder
        fun managerModule(managerModule: ManagerModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}