package com.lbtt2801.hearme.view.fragments.homeactionmenu

import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
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
import com.lbtt2801.hearme.viewmodel.*


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var musicAdapter: MusicAdapter
    private lateinit var artistAdapter: ArtistAdapter
    private lateinit var chartAdapter: ChartAdapter
    private lateinit var mainActivity: MainActivity
    private var email: String? = ""
    private var drawableAvatar: Int? = null
    private var urlAvatar: String? = null
    private var fullName: String? = ""

    private val musicViewModel: MusicViewModel by activityViewModels()
    private val artistViewModel: ArtistViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()
    private val topicSearchViewModel: TopicSearchViewModel by activityViewModels()

    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            email = savedInstanceState.getString("email").toString()
        }
        val policy = ThreadPolicy.Builder()
            .permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }

    override fun onResume() {
        super.onResume()
        mainActivity = activity as MainActivity
        email = mainActivity.email

        drawableAvatar = userViewModel.lstDataUser.value?.first { it.email == email }?.avatar
        if (drawableAvatar == null) {
            urlAvatar = userViewModel.lstDataUser.value?.first { it.email == email }?.avatarUrl
        }
        fullName = userViewModel.lstDataUser.value?.first { it.email == email }?.fullName

        binding.tvSeeTrendingNow.setOnClickListener {
            findNavController().navigate(R.id.trendingNowFragment)
        }

        binding.tvSeePopularArtists.setOnClickListener {
            findNavController().navigate(R.id.popularArtistsFragment)
        }

        musicViewModel.lstDataMusics.observe((activity as MainActivity), Observer { list ->
            displayRecyclerViewMusic(list.sortedByDescending { it.totalListeners }
                .take(5) as ArrayList<Music>)
            if (list.isEmpty())
                Toast.makeText(context, "list Music is null or empty", Toast.LENGTH_SHORT).show()
        })

        artistViewModel.lstDataArtists.observe((activity as MainActivity), Observer { list ->
            displayRecyclerViewArtist(list.sortedByDescending { it.totalNumberOfListeners }
                .take(5) as ArrayList<Artist>)
            if (list.isEmpty())
                Toast.makeText(context, "list Artist is null or empty", Toast.LENGTH_SHORT).show()
        })

        viewModel.lstDataChart.observe((activity as MainActivity), Observer { list ->
            displayRecyclerViewChart(list as ArrayList<Chart>)
            if (list.isEmpty())
                Toast.makeText(context, "list Chart is null or empty", Toast.LENGTH_SHORT).show()
        })
        viewModel.getListDataChart()

        mainActivity.showBottomNav("VISIBLE")

        mainActivity.customToolbar(
            "VISIBLE",
            null,
            fullName,
            R.color.transparent,
            navIcon = if (drawableAvatar != null) {
                ContextCompat.getDrawable(requireContext(), drawableAvatar!!)
            } else {
                null
            },
            navIconUrl = if (urlAvatar != null) {
                urlAvatar
            } else {
                null
            },
            showIcMore = false,
            showIcFilter = false,
            showIcSearch = true,
            showIcNotification = true
        )

        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            //navigate
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("email", email)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun displayRecyclerViewMusic(lstData: ArrayList<Music>) {
        val layoutRecyclerViewMusic =
            LinearLayoutManager(view?.context, LinearLayoutManager.HORIZONTAL, false)
        musicAdapter = MusicAdapter(lstData, 0, this)
        binding.recyclerViewTrending.apply {
            layoutManager = layoutRecyclerViewMusic
            adapter = musicAdapter
        }
    }

    private fun displayRecyclerViewArtist(lstData: ArrayList<Artist>) {
        val layoutRecyclerViewArtist =
            LinearLayoutManager(view?.context, LinearLayoutManager.HORIZONTAL, false)
        artistAdapter = ArtistAdapter(lstData, 0)
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

    fun getImagePath(cr: ContentResolver, uri: Uri?): String? {
        var result: String? = null
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cur = cr.query(uri!!, projection, null, null, null)
        if (cur != null) {
            val columnIndex = cur
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cur.moveToFirst()
            result = cur.getString(columnIndex)
            cur.close()
        }
        return result
    }
}