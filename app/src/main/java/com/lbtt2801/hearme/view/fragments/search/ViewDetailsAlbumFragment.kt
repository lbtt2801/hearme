package com.lbtt2801.hearme.view.fragments.search

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.MoreSongData
import com.lbtt2801.hearme.data.adapter.MoreSongDropdownAdapter
import com.lbtt2801.hearme.data.adapter.MusicAdapter
import com.lbtt2801.hearme.databinding.FragmentViewDetailsAlbumBinding
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.viewmodel.MusicViewModel
import com.lbtt2801.hearme.viewmodel.UserViewModel


class ViewDetailsAlbumFragment : Fragment() {
    private lateinit var binding: FragmentViewDetailsAlbumBinding
    private lateinit var mainActivity: MainActivity

    private val musicViewModel: MusicViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()

    private lateinit var musicID: String
    private lateinit var music: Music
    private lateinit var email: String

    private lateinit var musicAdapter: MusicAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_view_details_album,
            container,
            false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = activity as MainActivity
        musicID = arguments?.getString("musicID").toString()
        email = mainActivity.email
        music = musicViewModel.lstDataMusics.value?.first { it.musicID == musicID }!!
        binding.music = music

        displayRecyclerView()
        Handler(Looper.getMainLooper()).postDelayed({
            mainActivity.initSpinnerMore(binding.spinnerDropDownMore,
                music,
                1,
                this)
        }, 200)

        mainActivity.showBottomNav("GONE")
        mainActivity.customToolbar(
            "VISIBLE",
            navIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back),
            showIcMore = true
        )
        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }
    }

    private fun displayRecyclerView() {
        musicAdapter =
            MusicAdapter(musicViewModel.lstDataMusics.value?.filter { it.isAlbum && it.musicID != musicID } as ArrayList<Music>,
                6,
                this)
        binding.recyclerViewSongs.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = musicAdapter
        }
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putString("musicID", musicID)
//    }
}