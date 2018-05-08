package com.joker.firstapp.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.joker.firstapp.ui.account.fragment.AccountFragment
import com.joker.firstapp.ui.event.fragment.EventFragment
import com.joker.firstapp.ui.home.fragment.HomeFragment

/**
 * Created by Vincent;
 * Created on 2018/5/8;
 * DSC:
 */
class MainAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> EventFragment()
            2 -> AccountFragment()
            else -> HomeFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }
}