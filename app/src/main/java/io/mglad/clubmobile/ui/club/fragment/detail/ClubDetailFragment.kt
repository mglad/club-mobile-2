package io.mglad.clubmobile.ui.club.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.mglad.clubmobile.R
import io.mglad.clubmobile.base.BaseFragment
import io.mglad.clubmobile.databinding.FragmentClubDetailBinding
import io.mglad.clubmobile.ui.club.ClubActivity

class ClubDetailFragment: BaseFragment<ClubDetailPresenter, FragmentClubDetailBinding>(), ClubDetailView {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        val club = (activity as ClubActivity).club

        binding!!.club = club
        presenter.onViewCreated()

        return view
    }

    override fun layoutId(): Int = R.layout.fragment_club_detail

    override fun instantiatePresenter(): ClubDetailPresenter = ClubDetailPresenter(this)
}