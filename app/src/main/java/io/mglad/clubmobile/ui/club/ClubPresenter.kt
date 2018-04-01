package io.mglad.clubmobile.ui.club

import io.mglad.clubmobile.R
import io.mglad.clubmobile.UserManager
import io.mglad.clubmobile.base.BasePresenter
import io.mglad.clubmobile.model.Club
import io.mglad.clubmobile.network.ClubApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ClubPresenter(clubView: ClubView): BasePresenter<ClubView>(clubView) {
    private var subscription: Disposable? = null

    override fun onViewCreated() {
        super.onViewCreated()

        view.hideLoading()
        view.setupTabs()
    }
    fun leaveClubSelected() {
        view.showLeaveClubAlert()
    }

    fun leaveAlertPositive(club: Club) {
        subscription = clubApi
                .leaveClub(userManager.getSessionId()!!, club.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { _ -> view.done() },
                        { view.showError(R.string.unknown_error) }
                )
    }
}