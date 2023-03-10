package com.gun.hiltexample

import android.app.Application
import android.content.Intent
import com.gun.hiltexample.constant.Constants
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        grantUriPermission(
            Constants.PROVIDER_APP_PACKAGE,
            Constants.URI_EXAMPLE,
            Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
    }
}