package com.joker.firstapp.ui.toolbar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.joker.firstapp.R
import kotlinx.android.synthetic.main.activity_toolbar.*

class ToolbarActivity : AppCompatActivity() {

    var isRefresh = false
    lateinit var mSearchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar)
        activity_toolbar_toolbar.title = ""
        setSupportActionBar(activity_toolbar_toolbar)
        activity_toolbar_toolbar.apply {
            navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_black_24dp)
            setNavigationOnClickListener { Toast.makeText(this@ToolbarActivity, title, Toast.LENGTH_SHORT).show() }
        }
        initListener()

    }

    private fun initListener() {

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_1, menu)
        mSearchView = menu?.findItem(R.id.item_search)?.actionView as SearchView
        mSearchView.queryHint = "请输入搜索的内容"
        mSearchView.setIconifiedByDefault(true)
        val mSearchAutoComplete = mSearchView.findViewById(R.id.search_src_text) as SearchView.SearchAutoComplete
        mSearchAutoComplete.setHintTextColor(resources.getColor(R.color.c_BDBDBD))
        mSearchAutoComplete.setTextColor(resources.getColor(R.color.c_FFFFFF))
        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        mSearchView.setOnCloseListener {
            Log.e("setOnCloseListener", "close================")
            false
        }



        mSearchView.setOnSearchClickListener { }
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (isRefresh)
            menu?.getItem(1)?.title = "修改"
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_search -> {
                Toast.makeText(this@ToolbarActivity, item.title, Toast.LENGTH_SHORT).show()
            }
            R.id.item_bike -> {
                isRefresh = true
                invalidateOptionsMenu()
                Toast.makeText(this@ToolbarActivity, item.title, Toast.LENGTH_SHORT).show()
            }
            R.id.item_more -> {
                Toast.makeText(this@ToolbarActivity, item.title, Toast.LENGTH_SHORT).show()
            }
            R.id.item_delete -> {
                Toast.makeText(this@ToolbarActivity, item.title, Toast.LENGTH_SHORT).show()
            }
            R.id.item_delete_group_1 -> {
                Toast.makeText(this@ToolbarActivity, item.title, Toast.LENGTH_SHORT).show()
            }
            R.id.item_delete_group_2 -> {
                Toast.makeText(this@ToolbarActivity, item.title, Toast.LENGTH_SHORT).show()
            }
            R.id.item_delete_group_3 -> {
                Toast.makeText(this@ToolbarActivity, item.title, Toast.LENGTH_SHORT).show()
            }
            R.id.item_radio -> {
                Toast.makeText(this@ToolbarActivity, item.title, Toast.LENGTH_SHORT).show()
            }
            R.id.item_music -> {
                Toast.makeText(this@ToolbarActivity, item.title, Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }


}
