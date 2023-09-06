package com.lbtt2801.hearme.view

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

class SongNotificationFragment : Fragment() {
    private var _binding: FragmentSongNotificationBinding? = null
    private val binding get() = _binding!!
    private lateinit var musicAdapter: MusicAdapter

    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_song_notification, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.lstDataMusic.observe((activity as MainActivity), Observer {
            displayRecyclerViewToday(it as ArrayList<Music>)

        })
        viewModel.getListDataMusic()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun displayRecyclerViewToday(lstData: ArrayList<Music>) {
        val layoutRecyclerViewMusic = LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
        musicAdapter = MusicAdapter(lstData, 1)
        binding.recyclerViewToday.apply {
            layoutManager = layoutRecyclerViewMusic
            adapter = musicAdapter
        }
    }

}