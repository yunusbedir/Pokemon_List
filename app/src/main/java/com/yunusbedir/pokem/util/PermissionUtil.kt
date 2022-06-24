package com.yunusbedir.pokem.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment


/**
 * check permission for Draw over other apps
 */
fun Activity.checkPermissionOverlay(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        Settings.canDrawOverlays(this)
    else
        false
}

/**
 * check permission for Draw over other apps
 */
fun Fragment.checkPermissionOverlay(): Boolean {
    return requireActivity().checkPermissionOverlay()
}

/**
 * request permission for Draw over other apps
 */
fun Activity.requestPermissionOverlay() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M  && !Settings.canDrawOverlays(this)) {
        this.startActivityForResult(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION), 2000)
    }
}

/**
 * request permission for Draw over other apps
 */
fun Fragment.requestPermissionOverlay() {
    requireActivity().requestPermissionOverlay()
}