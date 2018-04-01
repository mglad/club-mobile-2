package io.mglad.clubmobile.ui.dashboard

import io.mglad.clubmobile.R
import io.mglad.clubmobile.base.navigation.BaseNavigationPresenter
import io.mglad.clubmobile.model.Club
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DashboardPresenter(dashboardView: DashboardView) : BaseNavigationPresenter<DashboardView>(dashboardView) {
    private var subscription: Disposable? = null

    fun onViewResumed() {
        loadClubs()
    }

    private fun loadClubs() {
        view.showLoading()
        subscription = clubApi
                .getClubs(userManager.getSessionId()!!)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { clubList -> view.updateClubs(clubList) },
                        { view.showError(R.string.unknown_error) }
                )
    }

    override fun onViewDestroyed() {
        subscription?.dispose()
    }

    fun itemSelected(item: Club) {
        view.goToClub(item)
    }
}