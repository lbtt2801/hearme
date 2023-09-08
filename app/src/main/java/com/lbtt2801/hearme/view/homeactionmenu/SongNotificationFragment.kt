package com.lbtt2801.hearme.view.homeactionmenu

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.MusicAdapter
import com.lbtt2801.hearme.databinding.FragmentSongNotificationBinding
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.viewmodel.HomeViewModel
import java.text.SimpleDateFormat
import java.util.Date

class SongNotificationFragment : Fragment() {
    private var _binding: FragmentSongNotificationBinding? = null
    private val binding get() = _binding!!
    private lateinit var musicAdapter: MusicAdapter
    private var lstToday: ArrayList<Music> = ArrayList()
    private var lstYesterday: ArrayList<Music> = ArrayList()

    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_song_notification, container, false)
        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.lstDataMusic.observe((activity as MainActivity), Observer { musicList ->
            musicList as ArrayList<Music>
            val formatter = SimpleDateFormat("dd/MM/yyyy")
            val date = Date() // get date now
            val current = formatter.format(date) // format date now
            for (music: Music in musicList) {
                val release = formatter.format(music.release)
                if (current.compareTo(release) == 0)
                    lstToday.add(music)
                else lstYesterday.add(music)
            }
            displayRecyclerViewToday(lstToday)
            displayRecyclerViewYesterday(lstYesterday)
        })
        viewModel.getListDataMusic()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun displayRecyclerViewToday(lstData: ArrayList<Music>) {
        val layoutRecyclerViewMusic =
            LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
        musicAdapter =
            MusicAdapter(lstData.filter { it.category.categoryID != "ca002" } as ArrayList<Music>,
                1)
        binding.recyclerViewToday.apply {
            layoutManager = layoutRecyclerViewMusic
            adapter = musicAdapter
        }
    }

    private fun displayRecyclerViewYesterday(lstData: ArrayList<Music>) {
        val layoutRecyclerViewMusic =
            LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
        musicAdapter =
            MusicAdapter(lstData.filter { it.category.categoryID != "ca002" } as ArrayList<Music>,
                1)
        binding.recyclerViewYesterday.apply {
            layoutManager = layoutRecyclerViewMusic
            adapter = musicAdapter
        }
    }
}