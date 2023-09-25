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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.MusicAdapter
import com.lbtt2801.hearme.databinding.FragmentDownloadsBinding
import com.lbtt2801.hearme.model.Music
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

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        lst = userViewModel.lstDataUser.value?.first { it.email == email }?.listMusicsDownloaded

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

            binding.btnShuffle.setOnClickListener {
                lst!!.shuffle()
                findNavController().navigate(R.id.songPlayFragment,
                    Bundle().apply {
                        putString("musicID", lst!![0].musicID)
                    }
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
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