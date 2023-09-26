package com.lbtt2801.hearme

import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.view.fragments.library.SongFragment
import com.lbtt2801.hearme.view.fragments.search.SongPlayFragment

class MusicService: Service() {
    private  var myBinder = MyBinder()
    var mediaPlayer = MediaPlayer()
    private lateinit var mediaSession: MediaSessionCompat
    override fun onBind(p0: Intent?): IBinder {
        mediaSession = MediaSessionCompat(baseContext, "My Music")
        return myBinder
    }

    inner class MyBinder: Binder() {
        fun currentService(): MusicService {
            return this@MusicService
        }
    }

    fun showNotification(music: Music) {
        val notification = NotificationCompat.Builder(baseContext, MusicApplication.CHANNEL_ID)
            .setContentTitle(music.musicName)
            .setContentText(music.artist.artistName)
            .setSmallIcon(R.drawable.logo_nav)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.img_music)) //music.image))
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle().setMediaSession(mediaSession.sessionToken))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setOnlyAlertOnce(true)
            .addAction(R.drawable.ic_previous, "Previous", null)
            .addAction(R.drawable.ic_pause, "Pause", null)
            .addAction(R.drawable.ic_next, "Next", null)
            .build()
        startForeground(13, notification)
    }

    fun setup(check: Boolean){
        if (check)
            mediaPlayer.start()
        else
            mediaPlayer.stop()
    }

}