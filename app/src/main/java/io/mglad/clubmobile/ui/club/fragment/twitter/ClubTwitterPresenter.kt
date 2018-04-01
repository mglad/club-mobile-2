package io.mglad.clubmobile.ui.club.fragment.twitter

import io.mglad.clubmobile.R
import io.mglad.clubmobile.base.BasePresenter
import io.mglad.clubmobile.model.Club
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ClubTwitterPresenter(clubTwitterView: ClubTwitterView) : BasePresenter<ClubTwitterView>(clubTwitterView) {
    private var subscription: Disposable? = null

    fun onViewCreated(club: Club) {
        super.onViewCreated()

        loadDetails(club)
    }

    private fun loadDetails(club: Club) {
        view.showLoading()
        subscription = clubApi
                .getClubDetails(userManager.getSessionId()!!, club.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { clubDetails -> view.updateTweets(clubDetails.tweets) },
                        { view.showError(R.string.unknown_error) }
                )
    }
}