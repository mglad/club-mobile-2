package io.mglad.clubmobile.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import io.mglad.clubmobile.R
import io.mglad.clubmobile.base.navigation.BaseNavigationDrawerActivity
import io.mglad.clubmobile.databinding.ActivityDashboardBinding
import io.mglad.clubmobile.model.Club
import io.mglad.clubmobile.ui.club.ClubActivity
import io.mglad.clubmobile.utils.ItemSelector

class DashboardActivity : BaseNavigationDrawerActivity<DashboardPresenter, ActivityDashboardBinding>(), DashboardView, ItemSelector<Club> {
    private val adapter = DashboardAdapter(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.adapter = adapter
        binding.layoutManager = LinearLayoutManager(this)
        binding.dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)

        presenter.onViewCreated()
    }

    override fun onResume() {
        super.onResume()

        presenter.onViewResumed()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun instantiatePresenter(): DashboardPresenter {
        return DashboardPresenter(this)
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun updateClubs(clubs: List<Club>) {
        adapter.updateClubs(clubs)
    }

    override fun showLoading() {
        binding.progressVisibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressVisibility = View.GONE
    }

    override fun layoutId(): Int = R.layout.activity_dashboard

    override fun title(): String = "My Clubs"

    override fun itemSelected(item: Club) {
        presenter.itemSelected(item)
    }

    override fun goToClub(club: Club) {
        val intent = Intent(this, ClubActivity::class.java)
        intent.putExtra("club", club)

        startActivity(intent)
    }
}
