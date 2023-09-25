package com.lbtt2801.hearme

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class NotificationSong: Application() {
    override fun onCreate() {
        super.onCreate()

        createNotification()
    }

    private fun createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("HEAR_ME_APP", "Hear me app", NotificationManager.IMPORTANCE_HIGH)
            val manager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}