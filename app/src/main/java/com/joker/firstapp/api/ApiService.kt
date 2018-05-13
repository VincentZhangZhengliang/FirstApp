package com.joker.firstapp.api

import com.joker.firstapp.ui.login.bean.LoginBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by Vincent;
 * Created on 2018/5/8;
 * DSC:
 */
interface ApiService {

    @FormUrlEncoded
    @POST("/user/login")
    fun login(@Field("username") username: String, @Field("password") password: String): Observable<LoginBean>


}