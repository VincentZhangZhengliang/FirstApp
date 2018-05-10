package com.joker.firstapp.ui.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.joker.firstapp.R
import com.joker.firstapp.dialog.PermissionDialog2
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.Rationale


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        AndPermission.with(this@SplashActivity).permission(android.Manifest.permission.READ_PHONE_STATE)
                .rationale(mRationale)
                .onGranted {}
                .onDenied { }
                .start()
    }


    private val mRationale = Rationale { context, permissions, executor ->
        val ft = supportFragmentManager.beginTransaction()
        val prev = supportFragmentManager.findFragmentByTag("dialog")
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)
        val dialog = PermissionDialog2(this@SplashActivity)
        dialog.show()

    }


}
