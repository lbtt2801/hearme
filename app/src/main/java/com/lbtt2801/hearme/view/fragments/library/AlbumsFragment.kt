package com.lbtt2801.hearme.view.fragments.library

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.PlaylistAdapter
import com.lbtt2801.hearme.databinding.FragmentAlbumsBinding
import com.lbtt2801.hearme.model.Playlist
import com.lbtt2801.hearme.viewmodel.UserViewModel

class AlbumsFragment : Fragment() {
    private lateinit var binding: FragmentAlbumsBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var playlistAdapter: PlaylistAdapter
    private var lst = ArrayList<Playlist>()
    private var email: String? = ""
    private var spinnerItems = arrayOf("Recently Added", "Added Before")

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_albums, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = (activity as MainActivity)
        email = mainActivity.email

        mainActivity.customToolbar(
            "VISIBLE",
            "Albums",
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

        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.style_spinner, spinnerItems)
        binding.spinner.adapter = spinnerAdapter

        userViewModel.lstDataPlaylist.observe((activity as MainActivity), Observer {
            lst = it as ArrayList<Playlist>
            val lstP1 = lst
            val lstP0 = ArrayList<Playlist>()
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
        })
    }

    private fun displayRecyclerView(lstData: ArrayList<Playlist>) {
        val layoutRecyclerViewMusic =
            LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
        playlistAdapter = PlaylistAdapter(lstData, 3)
        binding.recyclerView.apply {
            layoutManager = layoutRecyclerViewMusic
            adapter = playlistAdapter
        }
    }
}