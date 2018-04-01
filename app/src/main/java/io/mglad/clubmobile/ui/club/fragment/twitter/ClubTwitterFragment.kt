package io.mglad.clubmobile.ui.club.fragment.twitter

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.mglad.clubmobile.R
import io.mglad.clubmobile.base.BaseFragment
import io.mglad.clubmobile.databinding.FragmentClubTwitterBinding
import io.mglad.clubmobile.ui.club.ClubActivity

class ClubTwitterFragment: BaseFragment<ClubTwitterPresenter, FragmentClubTwitterBinding>(), ClubTwitterView {
    private var adapter : ClubTwitterAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        val club = (activity as ClubActivity).club
        adapter = ClubTwitterAdapter(context)

        binding!!.adapter = adapter
        binding!!.layoutManager = LinearLayoutManager(activity)
        binding!!.dividerItemDecoration = DividerItemDecoration(activity, LinearLayoutManager.VERTICAL)

        presenter.onViewCreated(club)

        return view
    }

    override fun layoutId(): Int = R.layout.fragment_club_twitter

    override fun instantiatePresenter(): ClubTwitterPresenter = ClubTwitterPresenter(this)

    override fun showLoading() {
        binding!!.progressVisibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding!!.progressVisibility = View.GONE
    }

    override fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun updateTweets(tweets: List<String>?) {
        if (tweets != null) {
            adapter!!.updateTweets(tweets)
        }
    }
}
