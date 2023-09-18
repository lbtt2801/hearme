package com.lbtt2801.hearme.view.fragments.library

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.ArtistAdapter
import com.lbtt2801.hearme.data.adapter.MusicAdapter
import com.lbtt2801.hearme.databinding.FragmentMyLibraryBinding
import com.lbtt2801.hearme.model.Artist
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.viewmodel.MusicViewModel

class MyLibraryFragment : Fragment() {
    private var _binding: FragmentMyLibraryBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity
    private lateinit var musicAdapter: MusicAdapter

    private val musicViewModel: MusicViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_library, container, false)

        mainActivity = (activity as MainActivity)

        mainActivity.showBottomNav("VISIBLE")
        mainActivity.customToolbar(
            "VISIBLE",
            "My Library",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.logo_nav),
            showIcMore = true,
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

        musicViewModel.lstDataMusics.observe((activity as MainActivity), Observer { list ->
            displayRecyclerView(list.sortedByDescending { it.totalListeners }
                .take(5) as ArrayList<Music>)
            if (list.isEmpty())
                Toast.makeText(context, "list Music is null or empty", Toast.LENGTH_SHORT).show()
        })

        binding.tvSeeAll.setOnClickListener {
            findNavController().navigate(R.id.action_item_nav_library_to_historyFragment)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun displayRecyclerView(lstData: ArrayList<Music>) {
        val layoutRecyclerViewMusic =
            LinearLayoutManager(view?.context, LinearLayoutManager.HORIZONTAL, false)
        musicAdapter = MusicAdapter(lstData, 0,this)
        binding.recyclerView.apply {
            layoutManager = layoutRecyclerViewMusic
            adapter = musicAdapter
        }
    }
}