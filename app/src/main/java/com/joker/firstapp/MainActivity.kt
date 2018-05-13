package com.joker.firstapp

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.gyf.barlibrary.ImmersionBar
import com.joker.firstapp.adapter.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener, BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ImmersionBar.with(this).keyboardEnable(true).navigationBarWithKitkatEnable(false).init()
        initView()
        initListener()
//        Api.login("VincentZhang", "zhang343568").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<LoginBean> {
//                    override fun onComplete() {
//                    }
//
//                    override fun onSubscribe(d : Disposable) {
//                    }
//
//                    override fun onNext(t : LoginBean) {
//                        Timber.e(t.toString())
//                    }
//
//                    override fun onError(e : Throwable) {
//
//                    }
//
//                })
    }

    private fun initListener() {
        activity_main_viewpager.addOnPageChangeListener(this@MainActivity)
        activity_main_bnv.setOnNavigationItemSelectedListener(this@MainActivity)
    }

    override fun onPageScrollStateChanged(state : Int) {
    }

    override fun onPageScrolled(position : Int, positionOffset : Float, positionOffsetPixels : Int) {
        //        activity_main_bnv.menu.getItem(position).isChecked = true
        when (position) {
            0 -> activity_main_bnv.menu.getItem(position).isChecked = true  //对应的判断操作
            1 -> activity_main_bnv.menu.getItem(position).isChecked = true
            2 -> activity_main_bnv.menu.getItem(position).isChecked = true
        }
    }

    override fun onPageSelected(position : Int) {
    }

    override fun onNavigationItemSelected(item : MenuItem) : Boolean {
        var result = false
        when (item.itemId) {
            R.id.item_home    -> {
                activity_main_viewpager.currentItem = 0
                result = true
            }
            R.id.item_event   -> {
                activity_main_viewpager.currentItem = 1
                result = true
            }
            R.id.item_account -> {
                activity_main_viewpager.currentItem = 2
                result = true
            }
        }
        return result
    }

    private fun initView() {
        activity_main_viewpager.adapter = MainAdapter(supportFragmentManager)
        activity_main_bnv.itemTextColor = resources.getColorStateList(R.color.selector_bottom_navi_text_color)
    }

}
