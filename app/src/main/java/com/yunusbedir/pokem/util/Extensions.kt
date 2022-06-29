package com.yunusbedir.pokem.util

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Parcelable
import android.provider.Settings
import androidx.core.content.ContextCompat
import com.google.firebase.analytics.FirebaseAnalytics
import com.yunusbedir.pokem.firebase.FirebaseEvent
import com.yunusbedir.pokem.service.ForegroundService

/** Start service for draw on overlay **/
fun <T : Parcelable> Context.startService(pokemonDetail: T?) {
    val intent = Intent(this, ForegroundService::class.java).putExtra(
        ForegroundService.EXTRA_NAME,
        pokemonDetail
    )
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (Settings.canDrawOverlays(this)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                ContextCompat.startForegroundService(
                    this, intent,
                )
            } else {
                startService(intent)
            }
        }
    } else {
        startService(intent)
    }
}
