package com.joker.firstapp.ui.login.bean

/**
 * Created by Vincent;
 * Created on 2018/5/10;
 * DSC:
 */

data class LoginBean(var errorCode: Int, var errorMsg: String, var data: LoginDataBean)

data class LoginDataBean(var collectIds: List<Int>, var email: String, var icon: String, var id: String, var password: String, var type: Int, var username: String)