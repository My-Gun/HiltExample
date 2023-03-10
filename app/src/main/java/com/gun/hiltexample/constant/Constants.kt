package com.gun.hiltexample.constant

import android.net.Uri
import com.gun.hiltexample.data.repository.AppDatabase.Companion.TABLE_NAME

object Constants {
    const val TAG: String = "HiltExample"

    private const val PROVIDER_AUTHORITY = "com.gun.hiltexampleforcontentprovider.provider"

    const val PROVIDER_APP_PACKAGE = "com.gun.hiltexampleforcontentprovider"
    val URI_EXAMPLE: Uri = Uri.parse("content://$PROVIDER_AUTHORITY/$TABLE_NAME")
}