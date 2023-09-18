package com.lbtt2801.hearme.view.fragments.library

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.MusicAdapter
import com.lbtt2801.hearme.databinding.FragmentLibraryPodcastBinding
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.viewmodel.HomeViewModel
import com.lbtt2801.hearme.viewmodel.UserViewModel

class LibraryPodcastFragment : Fragment() {
    private var _binding: FragmentLibraryPodcastBinding? = null
    private val binding get() = _binding!!
    private lateinit var musicAdapter: MusicAdapter
    private lateinit var mainActivity: MainActivity
    private var lst: ArrayList<Music>?= null
    private var email: String? = ""

    private val userViewModel: UserViewModel by activityViewModels()
    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_podcast_notification,
            container,
            false
        )
        lst = ArrayList()
        mainActivity = activity as MainActivity
        email = mainActivity.email
        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}