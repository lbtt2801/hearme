package com.lbtt2801.hearme.view.tab_viewpager

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import com.lbtt2801.hearme.databinding.FragmentTabSongBinding
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.viewmodel.HomeViewModel
import com.lbtt2801.hearme.viewmodel.UserViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TabSongFragment : Fragment() {
    private lateinit var binding: FragmentTabSongBinding
    private lateinit var musicAdapter: MusicAdapter
    private lateinit var mainActivity: MainActivity
    private var lst: ArrayList<Music>? = null
    private var email: String? = ""

    private val userViewModel: UserViewModel by activityViewModels()
//    private val viewModel by lazy {
//        ViewModelProvider(this)[HomeViewModel::class.java]
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_tab_song, container, false)
        lst = ArrayList()
        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onResume() {
        super.onResume()
        mainActivity = activity as MainActivity
        email = mainActivity.email
        if (mainActivity.checkInHistory) {
            binding.tvYesterday.isVisible = false
            binding.recyclerViewYesterday.isVisible = false
            lst = userViewModel.lstDataUser.value?.first { it.email == email }?.listMusicsListened  // list history
            lst = lst?.filter { it.category.categoryID != "ca002" } as ArrayList<Music>
            if (lst == null) {
                binding.tvToday.text = "There is no history of listening to Podcast !!"
                binding.recyclerViewToday.isVisible = false
                binding.tvToday.isVisible = true
                binding.tvYesterday.isVisible = false
                binding.recyclerViewYesterday.isVisible = false
                binding.recyclerViewToday.isVisible = false
            } else {
                binding.tvToday.isVisible = false
                binding.recyclerViewToday.isVisible = true
                displayRecyclerViewToday(lst!!, 5)
            }
        } else {
            mainActivity.viewModelMusic.lstDataMusics.observe((activity as MainActivity), Observer { musicList ->
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
                } as ArrayList<Music>, 1)
                displayRecyclerViewYesterday(lst?.filter {
                    formatter.format(
                        it.release
                    ).compareTo(
                        yesterdayDate
                    ) == 0 && it.category.categoryID != "ca002"
                } as ArrayList<Music>)
            })
//            viewModel.getListDataMusic()
        }
    }

    private fun displayRecyclerViewToday(lstData: ArrayList<Music>, type: Int) {
        val layoutRecyclerViewMusic =
            LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)

        musicAdapter = MusicAdapter(lstData, type, this)

        binding.recyclerViewToday.apply {
            layoutManager = layoutRecyclerViewMusic
            adapter = musicAdapter
        }
    }

    private fun displayRecyclerViewYesterday(lstData: ArrayList<Music>) {
        val layoutRecyclerViewMusic =
            LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
        musicAdapter =
            MusicAdapter(lstData, 5, this)
        binding.recyclerViewYesterday.apply {
            layoutManager = layoutRecyclerViewMusic
            adapter = musicAdapter
        }
    }
}