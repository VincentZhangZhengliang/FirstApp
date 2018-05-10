package com.joker.firstapp.ui.home.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joker.firstapp.R
import com.joker.firstapp.ui.coordinator.activity.CoordinatorActivity
import com.joker.firstapp.ui.navigation.NavigationActivity
import com.joker.firstapp.ui.toolbar.ToolbarActivity
import com.joker.firstapp.ui.transition.TransitionActivity
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragment_home_tv_toolbar.setOnClickListener { startActivity(Intent(activity, ToolbarActivity::class.java)) }
        fragment_home_tv_navigation.setOnClickListener { startActivity(Intent(activity, NavigationActivity::class.java)) }
        fragment_home_tv_transition.setOnClickListener { startActivity(Intent(activity, TransitionActivity::class.java)) }
        fragment_home_tv_coordinator.setOnClickListener { startActivity(Intent(activity, CoordinatorActivity::class.java)) }
    }


}
