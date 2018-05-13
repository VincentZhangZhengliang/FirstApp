package com.joker.firstapp.ui.splash

import android.Manifest
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.bumptech.glide.Glide
import com.joker.firstapp.MainActivity
import com.joker.firstapp.R
import com.joker.firstapp.dialog.PermissionDialog2
import com.joker.firstapp.ui.splash.adapter.SplashAdapter
import com.joker.firstapp.utils.Constant
import com.joker.firstapp.utils.DensityUtil
import com.joker.firstapp.utils.Preference
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.Rationale
import com.yanzhenjie.permission.RequestExecutor
import com.yanzhenjie.permission.SettingService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.concurrent.TimeUnit


class SplashActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    var first : Boolean by Preference(Constant.FIRST_START, true)

        private val resId = intArrayOf(R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3, R.drawable.guide_4)
//    private val resId = intArrayOf(R.drawable.splash_1, R.drawable.splash_2, R.drawable.splash_3, R.drawable.splash_4)


    fun setUI(swatch : Palette.Swatch?) {
        val population = swatch?.population          //样本中的像素数量
        val rgb = swatch?.rgb                        //颜色的RBG值
        val hsl = swatch?.hsl                 // 颜色的HSL值
        val bodyTextColor = swatch?.bodyTextColor    //: 主体文字的颜色值
        val titleTextColor = swatch?.titleTextColor  //: 标题文字的颜色值
        activity_splash_btn_to_find.setTextColor(rgb ?: R.color.c_FFFFFF)
        activity_splash_btn_skip.setTextColor(rgb ?: R.color.c_FFFFFF)
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (first) {
            initDot(0)
            activity_splash_btn_skip.visibility = View.VISIBLE
            activity_splash_vp.adapter = SplashAdapter(this@SplashActivity, resId)
            initListener()
        } else {
            activity_splash_btn_skip.visibility = View.GONE
            val imgUrl = "https://img.topzrt.com/adv/img/5621b999-481c-4c4c-ba89-f62827ffbedd.jpg"
            Glide.with(this@SplashActivity).load(imgUrl).into(activity_splash_iv)
            Observable.timer(1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }
        }

    }

    private fun initDot(pos : Int) {
        activity_splash_ll_container.removeAllViews()
        for (i in 0 until resId.size) {
            val view = View(this@SplashActivity)
            val lp = LinearLayout.LayoutParams(DensityUtil.pxToDp(this@SplashActivity, 48), DensityUtil.pxToDp(this@SplashActivity, 48))
            if (i == resId.size - 1) {
                lp.setMargins(0, 0, 0, 0)
            } else {
                lp.setMargins(0, 0, DensityUtil.pxToDp(this@SplashActivity, 48), 0)
            }
            view.layoutParams = lp
            if (pos == i) {
                view.setBackgroundResource(R.drawable.shape_dot_selected)
            } else {
                view.setBackgroundResource(R.drawable.shape_dot_normal)
            }
            activity_splash_ll_container.addView(view)
        }
    }

    private fun initListener() {
        activity_splash_vp.addOnPageChangeListener(this)
        activity_splash_btn_to_find.setOnClickListener {
            skip()
        }
        activity_splash_btn_skip.setOnClickListener {
            skip()
        }
    }

    private fun skip() {
        AndPermission.with(this@SplashActivity).permission(Manifest.permission.READ_PHONE_STATE).rationale(mRationale).onGranted {
            first = false
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }.onDenied {
            if (AndPermission.hasAlwaysDeniedPermission(this@SplashActivity, it)) {
                val settingService = AndPermission.permissionSetting(this@SplashActivity)
                showPermissionDialog("权限说明", "该权限只用于实现基础的网络功能,请放心授权.", settingService)
            } else {
                Toast.makeText(this@SplashActivity, "您拒绝了权限申请,会影响您的使用体验.", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private val mRationale = Rationale { _, _, executor ->
        showPermissionDialog("权限说明", "该权限只用于实现基础的网络功能,请放心授权.", executor)
    }

    private fun showPermissionDialog(title : String, content : String, any : Any) {
        PermissionDialog2(this@SplashActivity).apply {
            setTitle(title)
            setContent(content)
            setOnNagetiveListener("拒绝", object : PermissionDialog2.OnNagetiveListener {
                override fun onClick() {
                    if (any is SettingService) {
                        any.cancel()
                    } else if (any is RequestExecutor) {
                        any.cancel()
                    }
                    dismiss()
                }
            })
            setOnPositiveListener("授权", object : PermissionDialog2.OnPositiveListener {
                override fun onClick() {
                    // 如果用户同意去设置：
                    if (any is SettingService) {
                        any.execute()
                    } else if (any is RequestExecutor) {
                        any.execute()
                    }
                    dismiss()
                }
            })
        }.show()
    }

    override fun onPageScrollStateChanged(state : Int) {
    }

    override fun onPageScrolled(position : Int, positionOffset : Float, positionOffsetPixels : Int) {
    }

    override fun onPageSelected(position : Int) {
        initDot(position)
        val bitmap = BitmapFactory.decodeResource(resources, resId[position])
        Palette.from(bitmap).generate(object : Palette.PaletteAsyncListener {
            override fun onGenerated(palette : Palette) {
                val vibrant = palette.vibrantSwatch //有活力的

                val vibrantDark = palette.darkVibrantSwatch //有活力的，暗色

                val vibrantLight = palette.lightVibrantSwatch //有活力的，亮色

                val muted = palette.mutedSwatch //柔和的

                val mutedDark = palette.darkMutedSwatch //柔和的，暗色

                val mutedLight = palette.lightMutedSwatch //柔和的,亮色
                setUI(vibrantLight)
            }

        })
        if (position == resId.size - 1) {
            activity_splash_btn_to_find.visibility = View.VISIBLE
            activity_splash_ll_container.visibility = View.GONE
        } else {
            activity_splash_ll_container.visibility = View.VISIBLE
            activity_splash_btn_to_find.visibility = View.GONE
        }
    }

}
