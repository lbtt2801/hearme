package com.lbtt2801.hearme.view.fragments.homeactionmenu

import android.content.ContentResolver
import android.content.ContentValues.TAG
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
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
import com.lbtt2801.hearme.viewmodel.ArtistViewModel
import com.lbtt2801.hearme.viewmodel.HomeViewModel
import com.lbtt2801.hearme.viewmodel.MusicViewModel
import com.lbtt2801.hearme.viewmodel.UserViewModel
import java.io.FileNotFoundException


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var musicAdapter: MusicAdapter
    private lateinit var artistAdapter: ArtistAdapter
    private lateinit var chartAdapter: ChartAdapter
    private lateinit var mainActivity: MainActivity
    private var email: String? = ""
    private var drawableAvatar: Int? = null
    private var uriAvatar: Uri? = null
    private var fullName: String? = ""

    private val musicViewModel: MusicViewModel by activityViewModels()
    private val artistViewModel: ArtistViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()

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
    }

    override fun onResume() {
        super.onResume()
        mainActivity = activity as MainActivity
        email = mainActivity.email

        drawableAvatar = userViewModel.lstDataUser.value?.first { it.email == email }?.avatar
        if (drawableAvatar == null) {
            uriAvatar = userViewModel.lstDataUser.value?.first { it.email == email }?.avatarUri
            Log.v(TAG, "UriAvatar -> $uriAvatar")
            Log.v(TAG,
                "get MediaStore Image Path -> ${
                    getImagePath(mainActivity.contentResolver,
                        uriAvatar)
                }")
            if (uriAvatar == null) {
                drawableAvatar = R.drawable.ellipse
            }
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
                mainActivity.changeSizeDrawable(drawableAvatar!!, 48, 48)
            } else {
                val bitmap = try {
                    MediaStore.Images.Media.getBitmap(mainActivity.contentResolver, uriAvatar)
                } catch (e: FileNotFoundException) {
                    BitmapFactory.decodeResource(mainActivity.resources, R.drawable.ellipse)
                }
                BitmapDrawable(mainActivity.changeSizeBitmap(bitmap, 128 * 3, 128 * 3))
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

    override fun onDestroyView() {
        super.onDestroyView()
//        mainActivity.checkInHome = false
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