package com.lbtt2801.hearme.view.fragments.library

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.ArtistsData
import com.lbtt2801.hearme.data.CategoriesData
import com.lbtt2801.hearme.data.adapter.MusicAdapter
import com.lbtt2801.hearme.databinding.FragmentDownloadsBinding
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.model.Time
import com.lbtt2801.hearme.viewmodel.MusicViewModel
import com.lbtt2801.hearme.viewmodel.UserViewModel
import java.util.Date

class DownloadsFragment : Fragment() {
    private var _binding: FragmentDownloadsBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity
    private lateinit var musicAdapter: MusicAdapter
    private var lst: ArrayList<Music>? = null
    private var email: String? = ""
    private var spinnerItems = arrayOf("Recently Downloaded", "Downloaded Before")

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_downloads, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = (activity as MainActivity)
        email = mainActivity.email

        mainActivity.customToolbar(
            "VISIBLE",
            "Downloads",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back),
            showIcMore = true,
            showIcFilter = false,
            showIcSearch = true
        )
        mainActivity.showBottomNav("GONE")
        mainActivity.binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.music = Music(
            "ms004",
            "Fake Music",
            0,
            "https://firebasestorage.googleapis.com/v0/b/hearme-app-16567.appspot.com/o/images%2Fmusics%2Fwithout_you.png?alt=media&token=e78ac2d2-7e0e-4373-84f1-5f2d3b7ef445&_gl=1*1gfxh8m*_ga*MTUyOTk3NDI1NS4xNjkzMzU5NjY4*_ga_CW55HF8NVT*MTY5NTg4NjQxNS43LjEuMTY5NTg4ODExOS40MC4wLjA.",
            Time(0, 4, 30),
            Date("02/5/2023"),
            CategoriesData.dataCategory().first { it.categoryID == "ca001" },
            ArtistsData.dataArtist().first { it.artistId == "ar001" },
            true
        )

        userViewModel.lstDataUser.observe(viewLifecycleOwner) {
            lst = it.first { user -> user.email == email }.listMusicsDownloaded
        }


        val lstP1 = lst as ArrayList<Music>
        val lstP0 = ArrayList<Music>()
        if (lst != null) {
            for (i in lst!!.indices) {
                lstP0.add(i, lst!![lst!!.lastIndex - i])
            }
        }

        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.style_spinner, spinnerItems)
        binding.spinner.adapter = spinnerAdapter

        if (lst != null) {
            binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (p2 == 0)
                        displayRecyclerView(lstP0)
                    else
                        displayRecyclerView(lstP1)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        } else {
            binding.btnPlay.isEnabled = false
            binding.btnShuffle.isEnabled = false
        }

        // Shuffle list
        val lstShuffle = lst as ArrayList
        lstShuffle.shuffle()
        binding.music =  lstShuffle[0]

        binding.btnShuffle.setOnClickListener {
            findNavController().navigate(R.id.songPlayFragment,
                Bundle().apply {
                    putString("musicID", lst!![0].musicID)
                }
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG, "onDestroy ")
        _binding = null
    }

    private fun displayRecyclerView(lstData: ArrayList<Music>) {
        val layoutRecyclerViewMusic =
            LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
        musicAdapter = MusicAdapter(lstData, 6, this)
        binding.recyclerView.apply {
            layoutManager = layoutRecyclerViewMusic
            adapter = musicAdapter
        }
    }
}