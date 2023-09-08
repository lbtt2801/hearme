package com.lbtt2801.hearme.view.homeactionmenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.MusicAdapter
import com.lbtt2801.hearme.databinding.FragmentTrendingNowBinding
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.viewmodel.HomeViewModel

class TrendingNowFragment : Fragment() {
    private var _binding: FragmentTrendingNowBinding? = null
    private val binding get() = _binding!!
    private lateinit var musicAdapter: MusicAdapter

    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_trending_now, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).customToolbar(
            "VISIBLE",
            "Trending Now",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back),
            showIcMore = false,
            showIcFilter = false,
            showIcSearch = true
        )

        (activity as MainActivity).binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }

        viewModel.lstDataMusic.observe((activity as MainActivity), Observer {
            displayRecyclerView(it as ArrayList<Music>)
        })
        viewModel.getListDataMusic()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun displayRecyclerView(lstData: ArrayList<Music>) {
        val layoutRecyclerView =
            GridLayoutManager(view?.context, 2, LinearLayoutManager.VERTICAL, false)
        musicAdapter = MusicAdapter(lstData, 0)
        binding.recyclerView.apply {
            layoutManager = layoutRecyclerView
            adapter = musicAdapter
        }
    }
}