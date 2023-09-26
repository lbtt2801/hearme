package com.lbtt2801.hearme.view.fragments.search

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.ContentValues.TAG
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.MusicService
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentSongPlayBinding
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.viewmodel.MusicViewModel
import java.util.concurrent.TimeUnit

class SongPlayFragment : Fragment(), ServiceConnection {
    private lateinit var binding: FragmentSongPlayBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var runnable: Runnable
    private lateinit var musicID: String
    private var handler = Handler()
//    private var mediaPlayer = MediaPlayer()
    private var positionSong = 1 // vi tri bai hat da chon
    private var lstData = ArrayList<Int>()
    private var music: Music? = null
    var musicService = MusicService()

    private val musicViewModel: MusicViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_song_play, container, false)
        mainActivity = (activity as MainActivity)

        mainActivity.customToolbar(
            "VISIBLE",
            "",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back), true
        )
        mainActivity.showBottomNav("GONE")
        mainActivity.binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        lstData.add(R.raw.shape_of_you_nokia)
        lstData.add(R.raw.shape_of_you_nokia)
        lstData.add(R.raw.shape_of_you_nokia)
        lstData.add(R.raw.shape_of_you_nokia)
        lstData.add(R.raw.shape_of_you_nokia)

        Log.v(TAG, "Create fragment")

        // For Starting Service
//        val intent = Intent(this, MusicService::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        musicID = arguments?.getString("musicID").toString()
        music = musicViewModel.lstDataMusics.value?.first { it.musicID == musicID }
//        binding.music = music
        binding.imgAvatar.background =
            music?.let { ContextCompat.getDrawable(requireContext(), it.image) }
        binding.tvTitle.text = music?.musicName
        binding.tvDetail.text = music?.artist?.artistName
        binding.btnPlay.isChecked = music?.isPlaying == true

    }

    override fun onResume() {
        super.onResume()

        Log.v(TAG, "onResume")
        Toast.makeText(context, "Size lstData: ${lstData.size}", Toast.LENGTH_SHORT).show()

//        mediaPlayer = MediaPlayer.create(context, R.raw.shape_of_you_nokia)

        setResourcesWithMusic()

        binding.seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, position: Int, changed: Boolean) {
                if (changed) {
                    musicService.mediaPlayer.seekTo(position)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

        runnable = Runnable {
            binding.seekBar.progress = musicService.mediaPlayer.currentPosition
            binding.tvTimeStart.text = convertToMMSS(musicService.mediaPlayer.currentPosition)
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)

        // music off because time run end
        musicService.mediaPlayer.setOnCompletionListener {
            binding.seekBar.progress = 0

//            // tu dong phat bai ke tiep con BUG
//            positionSong += 1
//            if (positionSong == lstData.size - 1)
//                return@setOnCompletionListener
//            mediaPlayer.reset()
//            setResourcesWithMusic()
        }


        binding.linearCache.setOnClickListener {
            binding.linearCache.isVisible = false
            binding.linearLyrics.isVisible = true
        }

        binding.btnPlay.setOnClickListener {
            if (!musicService.mediaPlayer.isPlaying) {
                musicService.mediaPlayer.start()
                music?.isPlaying = true
            } else {
                musicService.mediaPlayer.stop()
                music?.isPlaying = false
            }
        }

        binding.btnNext.setOnClickListener {
            if (positionSong == lstData.size - 1)
                return@setOnClickListener
            positionSong += 1
            musicService.mediaPlayer.reset()
            setResourcesWithMusic()

        }

        binding.btnPrevious.setOnClickListener {
            if (positionSong == 0)
                return@setOnClickListener
            positionSong -= 1
            musicService.mediaPlayer.reset()
            setResourcesWithMusic()
        }

        binding.btnForward.setOnClickListener {
            if (musicService.mediaPlayer.isPlaying)
                musicService.mediaPlayer.seekTo(musicService.mediaPlayer.currentPosition + 10000)
        }

        binding.btnBackward.setOnClickListener {
            if (musicService.mediaPlayer.isPlaying)
                musicService.mediaPlayer.seekTo(musicService.mediaPlayer.currentPosition - 10000)
        }
    }

    @SuppressLint("DefaultLocale")
    fun convertToMMSS(duration: Int): String {
        val millis = duration.toLong()
        return java.lang.String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
            TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG, "onDestroy")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        Log.v(TAG, "onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        Log.v(TAG, "onDetach")
    }

    private fun setResourcesWithMusic() {
//        showNotificationMedia(requireContext())
        if (music != null)
            mainActivity.showNotificationMedia(music!!)

        musicService.mediaPlayer = MediaPlayer.create(context, lstData[positionSong]) // get Song in positionSong

        binding.seekBar.progress = 0
        binding.seekBar.max = musicService.mediaPlayer.duration

        binding.tvTimeEnd.text = convertToMMSS(musicService.mediaPlayer.duration)


        if (music?.isPlaying == true)
            musicService.mediaPlayer.start()
//        mediaPlayer.start()

    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        val binder = service as MusicService.MyBinder
        musicService = binder.currentService()
    }

    override fun onServiceDisconnected(p0: ComponentName?) {
        musicService = MusicService()
    }

//    @SuppressLint("MissingPermission")
//    private fun showNotificationMedia(context: Context) {
//        val notificationManagerCompat = NotificationManagerCompat.from(context)
//        val mediaSession = MediaSessionCompat(context, "MediaNotification")
//
//        val notification = NotificationCompat.Builder(context, "HEAR_ME_APP")
//            // Show controls on lock screen even when user hides sensitive content.
////            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
//            .setSmallIcon(R.drawable.logo_default)
//            .setLargeIcon(music?.image?.let { BitmapFactory.decodeResource(resources, it) })
//            .setSubText("Hearme App")
//            .setContentTitle(music?.musicName)
//            .setContentText(music?.artist?.artistName)
////            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setCategory(NotificationCompat.CATEGORY_TRANSPORT)
//            // Add media control buttons that invoke intents in your media service
//            .addAction(R.drawable.ic_previous, "Previous", null) // #0
//            .addAction(R.drawable.ic_pause, "Pause", null) // #1
//            .addAction(R.drawable.ic_next, "Next", null) // #2
//            // Apply the media style template
//            .setStyle(androidx.media.app.NotificationCompat.MediaStyle()
//                .setShowActionsInCompactView(0,1,2/* #1: pause button \*/)
//                .setMediaSession(mediaSession.sessionToken))
//            .build()
//
//        notificationManagerCompat.notify(1, notification)
//    }
}