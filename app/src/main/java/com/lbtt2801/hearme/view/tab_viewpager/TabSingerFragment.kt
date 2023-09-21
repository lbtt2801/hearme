package com.lbtt2801.hearme.view.tab_viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.ArtistAdapter
import com.lbtt2801.hearme.databinding.FragmentTabSingerBinding
import com.lbtt2801.hearme.model.Artist
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.viewmodel.HomeViewModel
import com.lbtt2801.hearme.viewmodel.UserViewModel

class TabSingerFragment : Fragment() {
    private lateinit var binding: FragmentTabSingerBinding
    private lateinit var artistAdapter: ArtistAdapter
    private lateinit var mainActivity: MainActivity
    private var lst = ArrayList<Artist>()
    private var email: String? = ""
    private var spinnerItems = arrayOf("Recently Downloaded", "Downloaded Before")

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_tab_singer, container, false)
        mainActivity = activity as MainActivity
        email = mainActivity.email
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.style_spinner, spinnerItems)
        binding.spinner.adapter = spinnerAdapter

        userViewModel.lstDataUser.observe((activity as MainActivity), Observer { arrayList ->
            val lstMusic = arrayList.first { user -> user.email == email }.listMusicsDownloaded
            val lstMusicSong = lstMusic.filter { it.category.categoryID != "ca002"}
            lst.clear()
            for (i in lstMusicSong) {
                lst.add(i.artist)
            }

            if (lst.isNotEmpty()) {
                val lstP1 = lst
                val lstP0 = ArrayList<Artist>()

                for (i in lst.indices) {
                    lstP0.add(i, lst[lst.lastIndex - i])
                }

                binding.spinner.setSelection(0)
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
            }
        })

    }

    private fun displayRecyclerView(lstData: ArrayList<Artist>) {
        val layoutRecyclerView =
            LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)

        artistAdapter = ArtistAdapter(lstData, 6)

        binding.recyclerView.apply {
            layoutManager = layoutRecyclerView
            adapter = artistAdapter
        }
    }
}