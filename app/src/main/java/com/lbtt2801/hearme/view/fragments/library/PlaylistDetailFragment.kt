package com.lbtt2801.hearme.view.fragments.library

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.MusicAdapter
import com.lbtt2801.hearme.databinding.FragmentPlaylistDetailBinding
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.model.Playlist
import com.lbtt2801.hearme.viewmodel.HomeViewModel
import com.lbtt2801.hearme.viewmodel.MusicViewModel
import com.lbtt2801.hearme.viewmodel.UserViewModel
import java.text.SimpleDateFormat
import java.util.Calendar

class PlaylistDetailFragment : Fragment() {
    private var _binding: FragmentPlaylistDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity
    private lateinit var musicAdapter: MusicAdapter
    private var lst = ArrayList<Playlist>()
    private var idPlaylist = ""

    private val userViewModel: UserViewModel by activityViewModels()
    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_playlist_detail, container, false)

        mainActivity = (activity as MainActivity)

        mainActivity.customToolbar(
            "VISIBLE",
            null,
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back),
            showIcMore = false,
            showIcFilter = false,
            showIcSearch = true
        )

        mainActivity.binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.imgAvatar.background =
            arguments?.getInt("img")?.let { ContextCompat.getDrawable(requireContext(), it) }
        binding.tvTitle.text = arguments?.getString("name")
        binding.tvNumber.text = arguments?.getString("size")
        idPlaylist = arguments?.getString("id").toString()

        userViewModel.lstDataPlaylist.observe((activity as MainActivity), Observer { playlists ->
            lst = playlists as ArrayList<Playlist>
            var lstMusic: ArrayList<Music>? = null
            lstMusic = lst.find { it.playlistID == "pl001" }?.listMusic
            if (lstMusic != null) {
                displayRecyclerView(lstMusic)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun displayRecyclerView(lstData: ArrayList<Music>) {
        val layoutRecyclerViewMusic =
            LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
        musicAdapter = MusicAdapter(lstData, 1,this)
        binding.recyclerView.apply {
            layoutManager = layoutRecyclerViewMusic
            adapter = musicAdapter
        }
    }
}