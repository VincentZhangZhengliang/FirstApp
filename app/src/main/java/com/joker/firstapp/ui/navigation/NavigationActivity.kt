package com.joker.firstapp.ui.navigation

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.joker.firstapp.R
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        activity_navigation_toolbar.apply {
            title = ""
            setSupportActionBar(this)
            navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp)
            setNavigationOnClickListener { Toast.makeText(this@NavigationActivity, title, Toast.LENGTH_SHORT).show() }
        }
        val toggle = ActionBarDrawerToggle(this@NavigationActivity, activity_navigation_drawerlayout, activity_navigation_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        activity_navigation_drawerlayout.addDrawerListener(toggle)
        toggle.syncState()
        initListener()
    }

    private fun initListener() {
        activity_navigation_navigationview.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.item_bike -> {
                        Log.e("item_bike", "item_bike")
                        Toast.makeText(this@NavigationActivity, title, Toast.LENGTH_SHORT).show()
                    }
                    R.id.item_delete -> {
                        Log.e("item_delete", "item_delete")
                        Toast.makeText(this@NavigationActivity, title, Toast.LENGTH_SHORT).show()
                    }
                }
                return true
            }
        })
        activity_navigation_navigationview.getHeaderView(0).findViewById<ImageView>(R.id.layout_navigation_deader_iv_header).setOnClickListener {
            Log.e("getHeaderView", "111")
        }
        activity_navigation_navigationview.getHeaderView(0).findViewById<TextView>(R.id.layout_navigation_deader_tv_1).setOnClickListener {
            Log.e("getHeaderView", "222")
        }
        activity_navigation_navigationview.getHeaderView(0).findViewById<TextView>(R.id.layout_navigation_deader_tv_2).setOnClickListener {
            Log.e("getHeaderView", "333")
        }


    }


}
