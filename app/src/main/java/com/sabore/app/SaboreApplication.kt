package com.sabore.app

import android.app.Application
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SaboreApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Google Mobile Ads SDK for Free Tier banner ads
        MobileAds.initialize(this) {}
    }
}
