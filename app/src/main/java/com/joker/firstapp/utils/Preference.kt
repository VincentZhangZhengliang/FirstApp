package com.joker.firstapp.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import kotlin.math.log
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by Vincent;
 * Created on 2018/5/10;
 * DSC:
 */
class Preference<T>(val name : String, val defValue : T) : ReadWriteProperty<Any?, T> {

    companion object {
        lateinit var preferences : SharedPreferences
        fun setContext(context : Context) {
            preferences = context.getSharedPreferences(context.packageName + Constant.SHARED_NAME, Context.MODE_PRIVATE)
        }

        fun clear() {
            preferences.edit().clear().apply()
        }
    }

    override fun getValue(thisRef : Any?, property : KProperty<*>) : T = findPreference(name, defValue)
    override fun setValue(thisRef : Any?, property : KProperty<*>, value : T) = putPreference(name, value)

    @Suppress("UNCHECKED_CAST")
    private fun<U> findPreference(name : String, defValue : U) : U = with(preferences) {
        val res : Any = when (defValue) {
            is Long    -> getLong(name, defValue)
            is String  -> getString(name, defValue)
            is Int     -> getInt(name, defValue)
            is Boolean -> getBoolean(name, defValue)
            is Float   -> getFloat(name, defValue)
            else       -> throw IllegalArgumentException("This type can be saved into Preferences")
        }
        res as U
    }

    private fun<U> putPreference(name : String, defValue : U) = with(preferences.edit()) {
        when (defValue) {
            is Long    -> putLong(name, defValue)
            is String  -> putString(name, defValue)
            is Int     -> putInt(name, defValue)
            is Boolean -> putBoolean(name, defValue)
            is Float   -> putFloat(name, defValue)
            else       -> throw IllegalArgumentException("This type can be saved into Preferences")
        }.apply()
    }


}