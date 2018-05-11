package com.joker.firstapp.ui.splash

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import android.view.View
import android.widget.LinearLayout
import com.joker.firstapp.R
import com.joker.firstapp.dialog.PermissionDialog2
import com.joker.firstapp.ui.splash.adapter.SplashAdapter
import com.joker.firstapp.utils.Constant
import com.joker.firstapp.utils.DensityUtil
import com.joker.firstapp.utils.Preference
import com.yanzhenjie.permission.Rationale
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

//    private val resId = intArrayOf(R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3, R.drawable.guide_4)
    private val resId = intArrayOf(R.drawable.splash_1, R.drawable.splash_2, R.drawable.splash_3, R.drawable.splash_4)

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        initDot(position)
        val bitmap = BitmapFactory.decodeResource(resources, resId[position])
        Palette.from(bitmap).generate(object : Palette.PaletteAsyncListener {
            override fun onGenerated(palette: Palette?) {
                val vibrant = palette?.vibrantSwatch//有活力的

                val vibrantDark = palette?.darkVibrantSwatch//有活力的，暗色

                val vibrantLight = palette?.lightVibrantSwatch//有活力的，亮色

                val muted = palette?.mutedSwatch//柔和的

                val mutedDark = palette?.darkMutedSwatch//柔和的，暗色

                val mutedLight = palette?.lightMutedSwatch//柔和的,亮色
                setUI(vibrantLight)
            }
        })

    }

    fun setUI(swatch: Palette.Swatch?) {
        val population = swatch?.population          //样本中的像素数量
        val rgb = swatch?.rgb                        //颜色的RBG值
        val hsl = swatch?.hsl                 // 颜色的HSL值
        val bodyTextColor = swatch?.bodyTextColor    //: 主体文字的颜色值
        val titleTextColor = swatch?.titleTextColor  //: 标题文字的颜色值
        activity_splash_btn_to_find.setTextColor(rgb?:R.color.c_FFFFFF)
        activity_splash_btn_skip.setTextColor(rgb?:R.color.c_FFFFFF)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val first: Boolean by Preference(Constant.FIRST_START, true)
        if (first) {
            initDot(0)
            activity_splash_vp.adapter = SplashAdapter(this@SplashActivity, resId)
        } else {
            //TODO:拉去图片显示

        }
        initListener()

        /* AndPermission.with(this@SplashActivity)
                 .permission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                 .rationale(mRationale)
                 .onGranted {}
                 .onDenied {
                     if (AndPermission.hasAlwaysDeniedPermission(this@SplashActivity, it)) {
                         val settingService = AndPermission.permissionSetting(this@SplashActivity)
                         PermissionDialog2(this@SplashActivity).apply {
                             setTitle("权限说明")
                             setContent("所有的安静都是人造的冷清，所有的安静都是人造的冷清，所有的安静都是人造的冷清")
                             setOnNagetiveListener("拒绝", object : PermissionDialog2.OnNagetiveListener {
                                 override fun onClick() {
                                     // 如果用户不同意去设置：
                                     settingService.cancel()
                                     dismiss()
                                 }
                             })
                             setOnPositiveListener("授权", object : PermissionDialog2.OnPositiveListener {
                                 override fun onClick() {
                                     // 如果用户同意去设置：
                                     settingService.execute()
                                     dismiss()
                                 }
                             })
                         }.show()
                     }
                 }.start()*/
    }

    private fun initDot(pos: Int) {
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

    }

    private val mRationale = Rationale { _, _, executor ->
        PermissionDialog2(this@SplashActivity)
                .apply {
                    setTitle("权限说明")
                    setContent("所有的安静都是人造的冷清")
                    setOnNagetiveListener("拒绝", object : PermissionDialog2.OnNagetiveListener {
                        override fun onClick() {
                            executor.cancel()
                            dismiss()
                        }
                    })
                    setOnPositiveListener("授权", object : PermissionDialog2.OnPositiveListener {
                        override fun onClick() {
                            executor.execute()
                            dismiss()
                        }
                    })
                }.show()
    }


}
