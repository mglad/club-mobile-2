package io.mglad.clubmobile.ui.club

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import io.mglad.clubmobile.ui.club.fragment.announcement.AnnouncementFragment
import io.mglad.clubmobile.ui.club.fragment.detail.ClubDetailFragment
import io.mglad.clubmobile.ui.club.fragment.twitter.ClubTwitterFragment

class ClubViewPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    private val fragments = arrayListOf(ClubDetailFragment(), AnnouncementFragment(), ClubTwitterFragment())

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? = null
}