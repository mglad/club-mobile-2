package io.mglad.clubmobile.ui.findclub

import io.mglad.clubmobile.R
import io.mglad.clubmobile.UserManager
import io.mglad.clubmobile.base.navigation.BaseNavigationPresenter
import io.mglad.clubmobile.model.Club
import io.mglad.clubmobile.network.ClubApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FindClubPresenter(findClubView: FindClubView) : BaseNavigationPresenter<FindClubView>(findClubView) {
    private var loadSubscription: Disposable? = null
    private var joinSubscription: Disposable? = null

    override fun onViewCreated() {
        loadAllClubs()
    }

    private fun loadAllClubs() {
        view.showLoading()
        loadSubscription = clubApi
                .getAllClubs(userManager.getSessionId()!!)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { clubList -> view.updateClubs(clubList) },
                        { view.showError(R.string.unknown_error) }
                )
    }

    override fun onViewDestroyed() {
        loadSubscription?.dispose()
        joinSubscription?.dispose()
    }

    fun itemSelected(club: Club) {
        view.showJoinAlert(club)
    }

    fun joinAlertPositive(club: Club) {
        loadSubscription = clubApi
                .joinClub(userManager.getSessionId()!!, club.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { _ -> view.showJoinSuccess(club.name) },
                        { error -> view.showError(error.localizedMessage) }
                )
    }
}