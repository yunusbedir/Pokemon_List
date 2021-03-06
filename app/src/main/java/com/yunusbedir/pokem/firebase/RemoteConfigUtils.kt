package com.yunusbedir.pokem.firebase

import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteConfigUtils @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig
) {

    init {
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            AppCompatDelegate.setDefaultNightMode(
                if (getTheme() != DEFAULTS[IS_NIGHT_THEME]) {
                    AppCompatDelegate.MODE_NIGHT_YES
                } else
                    AppCompatDelegate.MODE_NIGHT_NO
            )
        }
    }

    private fun getTheme(): Boolean {
        return remoteConfig.getBoolean(IS_NIGHT_THEME)
    }

    companion object {
        private const val IS_NIGHT_THEME = "is_night_theme"

        val DEFAULTS = hashMapOf<String, Any>(
            IS_NIGHT_THEME to false
        )
    }
}