package io.mglad.clubmobile.ui.findclub

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import io.mglad.clubmobile.R
import io.mglad.clubmobile.base.navigation.BaseNavigationDrawerActivity
import io.mglad.clubmobile.databinding.ActivityFindClubBinding
import io.mglad.clubmobile.model.Club
import io.mglad.clubmobile.utils.AlertDialogHelper
import io.mglad.clubmobile.utils.ItemSelector
import kotlinx.android.synthetic.main.activity_find_club.*


class FindClubActivity: BaseNavigationDrawerActivity<FindClubPresenter, ActivityFindClubBinding>(), FindClubView, SearchView.OnQueryTextListener, ItemSelector<Club> {
    private val adapter = FindClubAdapter(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.adapter = adapter
        binding.layoutManager = LinearLayoutManager(this)
        binding.dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)

        presenter.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.onViewDestroyed()
    }

    override fun layoutId(): Int = R.layout.activity_find_club

    override fun title(): String = "Find clubs"

    override fun instantiatePresenter(): FindClubPresenter {
        return FindClubPresenter(this)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchMenuItem = menu.findItem(R.id.search)
        val searchView = searchMenuItem.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextSubmit(text: String): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        adapter.filter.filter(newText)
        return true
    }

    override fun showJoinSuccess(name: String) {
        Snackbar.make(find_club_view, "Joined $name", Snackbar.LENGTH_LONG).show()
    }

    override fun itemSelected(item: Club) {
        presenter.itemSelected(item)

    }

    override fun showJoinAlert(club: Club) {
        AlertDialogHelper.alertWithAction(this, "Join", "Are you sure you want to join ${club.name}", {
            presenter.joinAlertPositive(club)
        })
    }
}
