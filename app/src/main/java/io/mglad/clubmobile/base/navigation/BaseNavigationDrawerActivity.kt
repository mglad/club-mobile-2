package io.mglad.clubmobile.base.navigation

import android.annotation.SuppressLint
import android.content.Intent
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import io.mglad.clubmobile.R
import io.mglad.clubmobile.base.toolbar.BaseToolbarActivity
import io.mglad.clubmobile.model.User
import io.mglad.clubmobile.ui.dashboard.DashboardActivity
import io.mglad.clubmobile.ui.findclub.FindClubActivity
import io.mglad.clubmobile.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

abstract class BaseNavigationDrawerActivity<P : BaseNavigationPresenter<BaseNavigationView>, B : ViewDataBinding>  : BaseToolbarActivity<P, B>(), NavigationView.OnNavigationItemSelectedListener, BaseNavigationView {
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        toggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(toggle)
        nav_view.setNavigationItemSelectedListener(this)

        toggle.syncState()

        presenter.onViewCreated()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> return true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_my_clubs -> {
                presenter.selectedMyClubs()
            }
            R.id.nav_find_clubs -> {
                presenter.selectedFindClubs()
            }
            R.id.nav_sign_out -> {
                presenter.selectedSignOut()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun goToMyClubs() {
        if (this !is DashboardActivity) {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }
    }

    override fun goToFindClubs() {
        if (this !is FindClubActivity) {
            startActivity(Intent(this, FindClubActivity::class.java))
            finish()
        }
    }

    override fun goToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    @SuppressLint("SetTextI18n")
    override fun setNavHeader(user: User) {
        val view = nav_view.getHeaderView(0)
        val navName = view.findViewById<TextView>(R.id.nav_name)
        val navUsername = view.findViewById<TextView>(R.id.nav_username)

        navName.text = "${user.firstName} ${user.lastName}"
        navUsername.text = user.username
    }
}