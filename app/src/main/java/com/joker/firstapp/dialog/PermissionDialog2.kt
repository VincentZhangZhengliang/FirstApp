package com.joker.firstapp.dialog

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.joker.firstapp.R

/**
 * Created by Vincent;
 * Created on 2018/5/10;
 * DSC:
 */
class PermissionDialog2(context: Context) : AlertDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_permission)
    }

}