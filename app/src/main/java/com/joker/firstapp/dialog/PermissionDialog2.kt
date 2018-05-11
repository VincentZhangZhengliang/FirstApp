package com.joker.firstapp.dialog

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.joker.firstapp.R
import kotlinx.android.synthetic.main.dialog_permission.*


/**
 * Created by Vincent;
 * Created on 2018/5/10;
 * DSC:
 */
class PermissionDialog2(context: Context) : AlertDialog(context, R.style.MyDialog) {

    private var mContext: Context = context

    private lateinit var onPositiveListener: OnPositiveListener
    private lateinit var onNagetiveListener: OnNagetiveListener
    private var positiveBtnStr: String = "确认"
    private var nagetiveBtnStr: String = "取消"
    private var titleStr: String = ""
    private var contentStr: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_permission)

        initLayoutParams(mContext)
        initData()
        initListener()
    }

    private fun initListener() {
        dialog_permission_btn_cancel.setOnClickListener { onNagetiveListener.onClick() }
        dialog_permission_btn_confirm.setOnClickListener { onPositiveListener.onClick() }
    }

    private fun initData() {
        dialog_permission_tv_title.text = titleStr
        dialog_permission_tv_content.text = contentStr
        dialog_permission_btn_cancel.text = nagetiveBtnStr
        dialog_permission_btn_confirm.text = positiveBtnStr
    }

    fun setTitle(str: String?) {
        titleStr = str ?: ""
    }

    fun setContent(str: String?) {
        contentStr = str ?: ""
    }


    fun setOnPositiveListener(str: String?, listener: OnPositiveListener) {
        positiveBtnStr = str ?: "确认"
        onPositiveListener = listener
    }

    fun setOnNagetiveListener(str: String?, listener: OnNagetiveListener) {
        nagetiveBtnStr = str ?: "取消"
        onNagetiveListener = listener
    }

    interface OnPositiveListener {
        fun onClick()
    }

    interface OnNagetiveListener {
        fun onClick()
    }

    private fun initLayoutParams(context: Context) {
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        val windowManager = (context as Activity).windowManager
        val display = windowManager.defaultDisplay
        val lp = window!!.attributes
        // 设置dialog宽度为屏幕的4/5
        lp.width = display.width * 4 / 5
        // p.height = display.height / 3
        window!!.attributes = lp
//        window.setGravity(Gravity.BOTTOM)
    }

}


