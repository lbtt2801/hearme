package com.lbtt2801.hearme.view.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentViewArtistBinding

class ViewArtistFragment : Fragment() {
    private lateinit var binding: FragmentViewArtistBinding
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_view_artist, container, false
        )
        mainActivity = activity as MainActivity
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        mainActivity.showBottomNav("VISIBLE")
        mainActivity.customToolbar("GONE")
    }
}