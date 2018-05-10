package com.joker.firstapp.ui.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.joker.firstapp.R
import com.joker.firstapp.dialog.PermissionDialog2
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.Rationale


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        AndPermission.with(this@SplashActivity).permission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE).rationale(mRationale).onGranted {}.onDenied {
            if (AndPermission.hasAlwaysDeniedPermission(this@SplashActivity, it)) {
                val settingService = AndPermission.permissionSetting(this@SplashActivity)
                val dialog = PermissionDialog2(this@SplashActivity)
                dialog.apply {
                    setTitle("所有的安静都是人造的冷清")
                    setContent("所有的安静都是人造的冷清")
                    setOnNagetiveListener("拒绝", object : PermissionDialog2.OnNagetiveListener {
                        override fun onClick() {
                            // 如果用户不同意去设置：
                            settingService.cancel()
                            dismiss()
                        }
                    })
                    setOnPositiveListener("去授权", object : PermissionDialog2.OnPositiveListener {
                        override fun onClick() {
                            // 如果用户同意去设置：
                            settingService.execute()
                            dismiss()
                        }
                    })
                }
                dialog.show()
            }
        }.start()
    }

    private val mRationale = Rationale { _, _, executor ->
        PermissionDialog2(this@SplashActivity).apply {
            setTitle("所有的安静都是人造的冷清")
            setContent("所有的安静都是人造的冷清")
            setOnNagetiveListener("拒绝", object : PermissionDialog2.OnNagetiveListener {
                override fun onClick() {
                    executor.cancel()
                    dismiss()
                }
            })
            setOnPositiveListener("去授权", object : PermissionDialog2.OnPositiveListener {
                override fun onClick() {
                    executor.execute()
                    dismiss()
                }
            })
        }.show()
    }
}
