package com.lbtt2801.hearme.view

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.ArtistAdapter
import com.lbtt2801.hearme.data.adapter.ChartAdapter
import com.lbtt2801.hearme.data.adapter.MusicAdapter
import com.lbtt2801.hearme.databinding.FragmentHomeBinding
import com.lbtt2801.hearme.model.Artist
import com.lbtt2801.hearme.model.Chart
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var musicAdapter: MusicAdapter
    private lateinit var artistAdapter: ArtistAdapter
    private lateinit var chartAdapter: ChartAdapter

    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).binding.toolBar.isVisible = false
        (activity as MainActivity).showBottomNav("VISIBLE")

        viewModel.lstDataMusic.observe((activity as MainActivity), Observer {
            displayRecyclerViewMusic(it as ArrayList<Music>)
            Log.v(ContentValues.TAG, "size music: ${it.size}")
            if (it.isEmpty())
                Toast.makeText(context, "list Music is null or empty", Toast.LENGTH_SHORT).show()
        })
        viewModel.getListDataMusic()

        viewModel.lstDataArtist.observe((activity as MainActivity), Observer {
            displayRecyclerViewArtist(it as ArrayList<Artist>)
            Log.v(ContentValues.TAG, "size artist: ${it.size}")
            if (it.isEmpty())
                Toast.makeText(context, "list Artist is null or empty", Toast.LENGTH_SHORT).show()
        })
        viewModel.getListDataArtist()

        viewModel.lstDataChart.observe((activity as MainActivity), Observer {
            displayRecyclerViewChart(it as ArrayList<Chart>)
            Log.v(ContentValues.TAG, "size chart: ${it.size}")
            if (it.isEmpty())
                Toast.makeText(context, "list Chart is null or empty", Toast.LENGTH_SHORT).show()
        })
        viewModel.getListDataChart()

        binding.icNotification.setOnClickListener {
            Toast.makeText(context, "Click icNotification", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.notificationFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun displayRecyclerViewMusic(lstData: ArrayList<Music>) {
        val layoutRecyclerViewMusic =
            LinearLayoutManager(view?.context, LinearLayoutManager.HORIZONTAL, false)
        musicAdapter = MusicAdapter(lstData, 0)
        binding.recyclerViewTrending.apply {
            layoutManager = layoutRecyclerViewMusic
            adapter = musicAdapter
        }
    }

    private fun displayRecyclerViewArtist(lstData: ArrayList<Artist>) {
        val layoutRecyclerViewArtist = LinearLayoutManager(view?.context, LinearLayoutManager.HORIZONTAL, false)
        artistAdapter = ArtistAdapter(lstData, 0)
        binding.recyclerViewPopularArtists.apply {
            layoutManager = layoutRecyclerViewArtist
            adapter = artistAdapter
        }
    }

    private fun displayRecyclerViewChart(lstData: ArrayList<Chart>) {
        val layoutRecyclerViewChart = LinearLayoutManager(view?.context, LinearLayoutManager.HORIZONTAL, false)
        chartAdapter = ChartAdapter(lstData, 0)
        binding.recyclerViewTopCharts.apply {
            layoutManager = layoutRecyclerViewChart
            adapter = chartAdapter
        }
    }
}