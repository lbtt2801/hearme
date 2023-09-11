package com.lbtt2801.hearme.view.homeactionmenu

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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
import com.lbtt2801.hearme.viewmodel.UserViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var musicAdapter: MusicAdapter
    private lateinit var artistAdapter: ArtistAdapter
    private lateinit var chartAdapter: ChartAdapter
    private lateinit var mainActivity: MainActivity
    private var email: String? = ""
    private var avatar: Drawable? = null
    private var fullName: String? = ""

    private val userViewModel: UserViewModel by activityViewModels()

    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        mainActivity = activity as MainActivity
        mainActivity.checkInHome = true
        email = mainActivity.email
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newAvatar = userViewModel.lstDataUser.value?.first { it.email == email }?.avatar
        fullName = userViewModel.lstDataUser.value?.first { it.email == email }?.fullName

        avatar = newAvatar?.let { mainActivity.changeSizeBitmap(it, 48, 48) }

        binding.tvSeeTrendingNow.setOnClickListener {
            findNavController().navigate(R.id.trendingNowFragment)
        }

        binding.tvSeePopularArtists.setOnClickListener {
            findNavController().navigate(R.id.popularArtistsFragment)
        }

        viewModel.lstDataMusic.observe((activity as MainActivity), Observer {
            displayRecyclerViewMusic(it as ArrayList<Music>)
            if (it.isEmpty())
                Toast.makeText(context, "list Music is null or empty", Toast.LENGTH_SHORT).show()
        })
        viewModel.getListDataMusic()

        viewModel.lstDataArtist.observe((activity as MainActivity), Observer {
            displayRecyclerViewArtist(it as ArrayList<Artist>)
            if (it.isEmpty())
                Toast.makeText(context, "list Artist is null or empty", Toast.LENGTH_SHORT).show()
        })
        viewModel.getListDataArtist()

        viewModel.lstDataChart.observe((activity as MainActivity), Observer {
            displayRecyclerViewChart(it as ArrayList<Chart>)
            if (it.isEmpty())
                Toast.makeText(context, "list Chart is null or empty", Toast.LENGTH_SHORT).show()
        })
        viewModel.getListDataChart()

//
//        binding.icNotification.setOnClickListener {
//            findNavController().navigate(R.id.notificationFragment)
//        }
    }

    override fun onResume() {
        super.onResume()
        mainActivity.showBottomNav("VISIBLE")
        mainActivity.customToolbar(
            "VISIBLE",
            null,
            fullName,
            R.color.transparent,
            avatar,
            showIcMore = false,
            showIcFilter = false,
            showIcSearch = true,
            showIcNotification = true
        )
        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            //navigate
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainActivity.checkInHome = false
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
        val layoutRecyclerViewArtist =
            LinearLayoutManager(view?.context, LinearLayoutManager.HORIZONTAL, false)
        artistAdapter = ArtistAdapter(lstData, 0, userViewModel)
        binding.recyclerViewPopularArtists.apply {
            layoutManager = layoutRecyclerViewArtist
            adapter = artistAdapter
        }
    }

    private fun displayRecyclerViewChart(lstData: ArrayList<Chart>) {
        val layoutRecyclerViewChart =
            LinearLayoutManager(view?.context, LinearLayoutManager.HORIZONTAL, false)
        chartAdapter = ChartAdapter(lstData, 0)
        binding.recyclerViewTopCharts.apply {
            layoutManager = layoutRecyclerViewChart
            adapter = chartAdapter
        }
    }
}