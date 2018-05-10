package com.joker.firstapp.api

import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Vincent;
 * Created on 2018/5/8;
 * DSC:
 */
object RetrofitHelper {
    
    val REQUEST_BASE_URL = "http://wanandroid.com/"

    val apiService: ApiService = RetrofitHelper.getService(REQUEST_BASE_URL, ApiService::class.java)

    fun <T> getService(url: String, service: Class<T>): T = create(url).create(service)

    fun create(url: String): Retrofit {
        return RetrofitBuild(url, OkhttpHelper.getClient().build(), GsonConverterFactory.create(), RxJava2CallAdapterFactory.create()).retrofit
    }

    class RetrofitBuild(url: String, client: OkHttpClient, gsonFactory: GsonConverterFactory, callAdapterFactory: CallAdapter.Factory) {
        val retrofit: Retrofit = Retrofit.Builder().apply {
            baseUrl(url)
            client(client)
            addConverterFactory(gsonFactory)
            addCallAdapterFactory(callAdapterFactory)
        }.build()
    }


}