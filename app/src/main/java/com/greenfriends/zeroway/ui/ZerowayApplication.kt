package com.greenfriends.zeroway.ui

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class ZerowayApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this,"307669f1987730b12c5a548ce1573c4d")
    }
}