package com.joker.firstapp.base

import android.app.Application
import com.joker.firstapp.utils.Constant
import com.joker.firstapp.utils.Preference
import timber.log.Timber

/**
 * Created by Vincent;
 * Created on 2018/5/10;
 * DSC:
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Preference.setContext(applicationContext)
        if (Constant.INTERCEPTOR_ENABLE) {
            Timber.plant(Timber.DebugTree())
        }
    }

}