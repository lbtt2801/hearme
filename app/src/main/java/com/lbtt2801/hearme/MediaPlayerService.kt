package com.lbtt2801.hearme

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MediaPlayerService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)

    }

}
