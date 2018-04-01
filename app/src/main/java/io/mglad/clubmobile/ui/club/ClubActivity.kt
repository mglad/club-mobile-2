package io.mglad.clubmobile.ui.club

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import io.mglad.clubmobile.R
import io.mglad.clubmobile.base.toolbar.BaseToolbarActivity
import io.mglad.clubmobile.databinding.ActivityClubBinding
import io.mglad.clubmobile.model.Club
import io.mglad.clubmobile.utils.AlertDialogHelper
import kotlinx.android.synthetic.main.activity_club.*


class ClubActivity : BaseToolbarActivity<ClubPresenter, ActivityClubBinding>(), ClubView{
    lateinit var club: Club

    private var icons = listOf(R.drawable.ic_list, R.drawable.ic_announcement, R.drawable.ic_twitter)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        club = intent.getSerializableExtra("club") as Club

        presenter.onViewCreated()
    }

    override fun setupTabs() {
        val adapter = ClubViewPagerAdapter(supportFragmentManager)
        pager.adapter = adapter

        tabs.setupWithViewPager(pager)
        for (i in 0 until tabs.tabCount) {
            tabs.getTabAt(i)!!.setIcon(icons[i])
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.leave_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_leave) {
            presenter.leaveClubSelected()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun instantiatePresenter(): ClubPresenter {
        return ClubPresenter(this)
    }

    override fun title(): String = (intent.getSerializableExtra("club") as Club).name

    override fun layoutId(): Int = R.layout.activity_club

    override fun showLeaveClubAlert() {
        AlertDialogHelper.alertWithAction(this, "Leave", "Are you sure you want to leave ${club.name}?", {
            presenter.leaveAlertPositive(club)
        })
    }

    override fun showLoading() {
        binding.progressVisibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressVisibility = View.GONE
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun done() {
        finish()
    }
}
