package com.joker.firstapp.api

import com.joker.firstapp.ui.login.bean.LoginBean
import io.reactivex.Observable

/**
 * Created by Vincent;
 * Created on 2018/5/10;
 * DSC:
 */
object Api {

    fun login(username: String, password: String): Observable<LoginBean> {
        return RetrofitHelper.apiService.login(username, password)
    }

}