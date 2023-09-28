package com.lbtt2801.hearme.view.fragments.search

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.ContentValues.TAG
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.MusicService
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentSongPlayBinding
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.viewmodel.MusicViewModel
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit


class SongPlayFragment : Fragment(), ServiceConnection {
    lateinit var binding: FragmentSongPlayBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var runnable: Runnable
    private lateinit var musicID: String
    private var handler = Handler()
//    private var mediaPlayer = MediaPlayer()
    private var positionSong = 1 // vi tri bai hat da chon
//    private var lstData = ArrayList<Int>()
    private var music: Music? = null
    private var musicService: MusicService ?= null

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
//            findNavController().navigate(R.id.action_songPlayFragment_to_notificationFragment)
        }

        binding.tvLyrics.movementMethod = ScrollingMovementMethod()
//        lstData.add(R.raw.shape_of_you_nokia)
//        lstData.add(R.raw.shape_of_you_nokia)
//        lstData.add(R.raw.shape_of_you_nokia)
//        lstData.add(R.raw.shape_of_you_nokia)
//        lstData.add(R.raw.shape_of_you_nokia)

        Log.v(TAG, "Create fragment")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//         For Starting Service
        val intent = Intent(mainActivity, MusicService::class.java)
        activity?.bindService(intent, this, BIND_AUTO_CREATE)
        activity?.startService(intent)

        musicID = arguments?.getString("musicID").toString()
        music = musicViewModel.lstDataMusics.value?.first { it.musicID == musicID }
        binding.music = music
        positionSong = music?.path!!
//        binding.imgAvatar.background =
//            music?.let { ContextCompat.getDrawable(requireContext(), it.image) }
        val options = RequestOptions()
            .centerCrop()
            .error(R.drawable.ellipse)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontTransform()

        Glide.with(this)
            .load(music!!.image)
            .apply(options)
            .transition(DrawableTransitionOptions.withCrossFade(250))
            .into(binding.imgAvatar)

        binding.tvTitle.text = music?.musicName
        binding.tvDetail.text = music?.artist?.artistName
        binding.btnPlay.isChecked = music?.isPlaying == true

        mainActivity.mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
    }

    override fun onResume() {
        super.onResume()

        Log.v(TAG, "onResume")
//        Toast.makeText(context, "Size lstData: ${lstData.size}", Toast.LENGTH_SHORT).show()

//        mediaPlayer = MediaPlayer.create(context, R.raw.shape_of_you_nokia)

        setResourcesWithMusic()

        binding.seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, position: Int, changed: Boolean) {
                if (changed) {
                    mainActivity.mediaPlayer.seekTo(position)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

        runnable = Runnable {
            binding.seekBar.progress = mainActivity.mediaPlayer.currentPosition
            binding.tvTimeStart.text = convertToMMSS(mainActivity.mediaPlayer.currentPosition)
            handler.postDelayed(runnable, 500)
        }
        handler.postDelayed(runnable, 500)

        // music off because time run end
        mainActivity.mediaPlayer.setOnCompletionListener {
            binding.seekBar.progress = 0

//            // tu dong phat bai ke tiep con BUG
//            positionSong += 1
//            if (positionSong == lstData.size - 1)
//                return@setOnCompletionListener
//            mediaPlayer.reset()
//            setResourcesWithMusic()
        }


        binding.linearCache.setOnClickListener {
            binding.linearDetail.isVisible = false
            binding.linearCache.isVisible = false
            binding.linearLyrics.isVisible = true
            var fileInputStream: FileInputStream ?= null
            fileInputStream = mainActivity.openFileInput("lyrics.txt")
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder = StringBuilder()
            var text: String ?= null
            while (run {
                    text = bufferedReader.readLine()
                    text
                } != null) {
                stringBuilder.append(text)
            }

            binding.tvLyrics.text = stringBuilder.toString()
        }

        binding.btnPlay.setOnClickListener {
//            if (!mediaPlayer.isPlaying) {
//                mediaPlayer.start()
//                music?.isPlaying = true
//            } else {
//                mediaPlayer.pause()
//                music?.isPlaying = false
//            }

//            musicService.mediaPlayer.pause()
//            musicService.setup(false)

            if (!mainActivity.mediaPlayer.isPlaying) {
                mainActivity.mediaPlayer.start()
                music?.isPlaying = true
            } else {
                mainActivity.mediaPlayer.pause()
                music?.isPlaying = false
            }
        }

        binding.btnNext.setOnClickListener {
            if (positionSong == mainActivity.dataListSong.size - 1)
                return@setOnClickListener
            positionSong += 1
            mainActivity.mediaPlayer.stop()
            mainActivity.mediaPlayer = MediaPlayer.create(context, mainActivity.dataListSong[positionSong])
            mainActivity.mediaPlayer.start()
        }

        binding.btnPrevious.setOnClickListener {
            if (positionSong == 0)
                return@setOnClickListener
            positionSong -= 1
            mainActivity.mediaPlayer.stop()
            mainActivity.mediaPlayer = MediaPlayer.create(context, mainActivity.dataListSong[positionSong])
            mainActivity.mediaPlayer.start()
        }

        binding.btnForward.setOnClickListener {
            if (mainActivity.mediaPlayer.isPlaying)
                mainActivity.mediaPlayer.seekTo(mainActivity.mediaPlayer.currentPosition + 10000)
        }

        binding.btnBackward.setOnClickListener {
            if (mainActivity.mediaPlayer.isPlaying)
                mainActivity.mediaPlayer.seekTo(mainActivity.mediaPlayer.currentPosition - 10000)
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
//        if (music != null)
//            mainActivity.showNotificationMedia(music!!) src="https://cdn.pixabay.com/audio/2023/09/25/audio_b475db93de.mp3"

//        mediaPlayer = MediaPlayer.create(context, lstData[positionSong]) // get Song in positionSong
//        mediaPlayer.setDataSource(requireContext(), Uri.parse("https://cdn.pixabay.com/audio/2023/09/25/audio_b475db93de.mp3"))


        binding.seekBar.progress = 0
        binding.seekBar.max = mainActivity.mediaPlayer.duration

        binding.tvTimeEnd.text = convertToMMSS(mainActivity.mediaPlayer.duration)

        if (music?.isPlaying == true)
            mainActivity.mediaPlayer.start()

//        musicService.mediaPlayer = MediaPlayer.create(context, lstData[1])
//        if (music?.isPlaying == true)
//            musicService.mediaPlayer.start()

    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        val binder = service as MusicService.MyBinder
        musicService = binder.currentService()
        musicService?.showNotification(music!!)
        Log.v(TAG, "onServiceConnected")
    }

    override fun onServiceDisconnected(p0: ComponentName?) {

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