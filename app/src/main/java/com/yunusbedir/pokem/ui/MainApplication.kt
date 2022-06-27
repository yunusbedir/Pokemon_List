package com.yunusbedir.pokem.ui

import android.app.Application
import com.yunusbedir.pokem.firebase.RemoteConfigUtils
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application() {

    @Inject
    lateinit var remoteConfigUtils: RemoteConfigUtils
}