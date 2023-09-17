package com.lbtt2801.hearme.view.fragments.homeactionmenu

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.MusicAdapter
import com.lbtt2801.hearme.databinding.FragmentSongTabBinding
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.viewmodel.HomeViewModel
import com.lbtt2801.hearme.viewmodel.UserViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TabSongFragment : Fragment() {
    private var _binding: FragmentSongTabBinding? = null
    private val binding get() = _binding!!
    private lateinit var musicAdapter: MusicAdapter
    private lateinit var mainActivity: MainActivity
    private var lst: ArrayList<Music>? = null
    private var email: String? = ""

    private val userViewModel: UserViewModel by activityViewModels()
    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_song_tab, container, false)
        lst = ArrayList()
        mainActivity = activity as MainActivity
        mainActivity.checkInHistory = true
        email = mainActivity.email
        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.lstDataMusic.observe((activity as MainActivity), Observer { musicList ->
            lst = musicList as ArrayList<Music>
            val formatter = SimpleDateFormat("dd/MM/yyyy")
            val calToday = Calendar.getInstance()
            val calYesterday = Calendar.getInstance()
            calYesterday.add(Calendar.DATE, -1)
            val currentDate = formatter.format(calToday.time) // format date now
            val yesterdayDate = formatter.format(calYesterday.time) // format yesterday date

            displayRecyclerViewToday(lst?.filter {
                formatter.format(
                    it.release
                ).compareTo(
                    currentDate
                ) == 0 && it.category.categoryID != "ca002"
            } as ArrayList<Music>)
            displayRecyclerViewYesterday(lst?.filter {
                formatter.format(
                    it.release
                ).compareTo(
                    yesterdayDate
                ) == 0 && it.category.categoryID != "ca002"
            } as ArrayList<Music>)
        })
        viewModel.getListDataMusic()

        if (mainActivity.checkInHistory) {
            binding.tvYesterday.isVisible = false
            binding.recyclerViewYesterday.isVisible = false
            lst = userViewModel.lstDataUser.value?.first { it.email == email }?.listMusicsLoved  // list history
            if (lst == null) {
                binding.tvToday.text = "There is no history of listening to Song !!"
                binding.recyclerViewToday.isVisible = false
            } else {
                binding.tvToday.isVisible = false
                displayRecyclerViewToday(lst!!)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainActivity.checkInHistory = false
        _binding = null
    }

    private fun displayRecyclerViewToday(lstData: ArrayList<Music>) {
        val layoutRecyclerViewMusic =
            LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
        musicAdapter =
            MusicAdapter(lstData, 1)
        binding.recyclerViewToday.apply {
            layoutManager = layoutRecyclerViewMusic
            adapter = musicAdapter
        }
    }

    private fun displayRecyclerViewYesterday(lstData: ArrayList<Music>) {
        val layoutRecyclerViewMusic =
            LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
        musicAdapter =
            MusicAdapter(lstData, 1)
        binding.recyclerViewYesterday.apply {
            layoutManager = layoutRecyclerViewMusic
            adapter = musicAdapter
        }
    }
}