package com.joker.firstapp.ui.transition

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.joker.firstapp.R
import kotlinx.android.synthetic.main.activity_transition.*

class TransitionActivity : AppCompatActivity() {

    lateinit var mAdapter: TransitionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition)
        activity_transition_recyclerview.layoutManager = LinearLayoutManager(this@TransitionActivity)
        mAdapter = TransitionAdapter(this@TransitionActivity)
        activity_transition_recyclerview.adapter = mAdapter

        mAdapter.setOnItemClickListener(object : TransitionAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                Log.e("onItemClick", position.toString())
                val iv = view.findViewById<ImageView>(R.id.item_transition_iv)
                val tv = view.findViewById<TextView>(R.id.item_transition_tv_title)
                val intent = Intent(this@TransitionActivity, TransitionContentActivity::class.java)
                val pair1 = Pair(iv!! as View, "shared_image")
                val pair2 = Pair(tv!! as View, "shared_textview")
                val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this@TransitionActivity, pair1, pair2)
                this@TransitionActivity.startActivity(intent, optionsCompat.toBundle())
            }

            override fun onItemLongClick(view: View, position: Int) {
                Log.e("onItemLongClick", position.toString())
            }

        })
    }

}
