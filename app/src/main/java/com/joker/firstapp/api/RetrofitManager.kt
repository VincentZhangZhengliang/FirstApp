package com.joker.firstapp.api

import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Vincent;
 * Created on 2018/5/8;
 * DSC:
 */
object RetrofitHelper {

    private const val TAG = "RetrofitHelper"
    private const val CONTENT_PRE = "OkHttp: "
    private const val SAVE_USER_LOGIN_KEY = "user/login"
    private const val SAVE_USER_REGISTER_KEY = "user/register"
    private const val SET_COOKIE_KEY = "set-cookie"
    private const val COOKIE_NAME = "Cookie"
    private const val CONNECT_TIMEOUT = 30L
    private const val READ_TIMEOUT = 10L

    fun getRetrofitClient(): ApiService {
        return Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
    }

//    val retrofitService: ApiService = RetrofitHelper.getService("", ApiService::class.java)

//    fun <T> getService(url: String, service: Class<T>): T = create(url).create(service)

    fun create(url: String) {

        val okHttpClientBuilder = OkHttpClient().newBuilder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            // get response cookie
            addInterceptor {
                val request = it.request()
                val response = it.proceed(request)
                val requestUrl = request.url().toString()
                val domain = request.url().host()
                // set-cookie maybe has multi, login to save cookie
                if ((requestUrl.contains(SAVE_USER_LOGIN_KEY) || requestUrl.contains(SAVE_USER_REGISTER_KEY))
                        && !response.headers(SET_COOKIE_KEY).isEmpty()) {
//                    val cookies = response.headers(SET_COOKIE_KEY)
//                    val cookie = encodeCookie(cookies)
//                    saveCookie(requestUrl, domain, cookie)
                }
                response
            }
            // set request cookie
            addInterceptor {
                val request = it.request()
                val builder = request.newBuilder()
                val domain = request.url().host()
                // get domain cookie
                if (domain.isNotEmpty()) {
//                    val spDomain: String by Preference(domain, "")
//                    val cookie: String = if (spDomain.isNotEmpty()) spDomain else ""
//                    if (cookie.isNotEmpty()) {
//                        builder.addHeader(COOKIE_NAME, cookie)
//                    }
                }
                it.proceed(builder.build())
            }
//            // add log print
//            if (Constant.INTERCEPTOR_ENABLE) {
//                // loggingInterceptor
//                addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
//                    loge(TAG, CONTENT_PRE + it)
//                }).apply {
//                    // log level
//                    level = HttpLoggingInterceptor.Level.BODY
//                })
//            }


        }
    }


    class RetrofitBuild(url: String, client: OkHttpClient, gsonFactory: GsonConverterFactory, callAdapterFactory: CallAdapter.Factory) {

    }


}