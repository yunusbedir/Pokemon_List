package com.yunusbedir.pokem.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.yunusbedir.pokem.R
import com.yunusbedir.pokem.domain.model.result.PokemonDetailResult
import com.yunusbedir.pokem.ui.popuppokemon.PopupPokemonWindow
import com.yunusbedir.pokem.util.WindowListener


class ForegroundService : Service(), WindowListener {
    private lateinit var window: PopupPokemonWindow

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            startMyOwnForeground()
        else startForeground(
            1,
            Notification()
        )

        window = PopupPokemonWindow(this, this)
        window.open()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val pokemonDetail = intent?.getParcelableExtra<PokemonDetailResult>(EXTRA_NAME)
        if (pokemonDetail != null) {
            window.update(pokemonDetail)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startMyOwnForeground() {
        val manager = (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        manager.createNotificationChannel(channel)
        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        val notification: Notification = notificationBuilder.setOngoing(true)
            .setContentTitle("Service running")
            .setContentText("Displaying over other apps")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationManager.IMPORTANCE_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .build()
        startForeground(2, notification)
    }

    override fun closed() {
        stopForeground(true)
        stopService(Intent(this, ForegroundService::class.java))
    }

    companion object {

        const val NOTIFICATION_CHANNEL_ID = "com.yunusbedir.pokem"
        private const val CHANNEL_NAME = "Background Service"
        const val EXTRA_NAME = "pokemon_detail"

        @RequiresApi(Build.VERSION_CODES.O)
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_MIN
        )
    }
}