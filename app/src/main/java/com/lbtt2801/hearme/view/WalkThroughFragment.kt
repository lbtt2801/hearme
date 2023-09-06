package com.lbtt2801.hearme.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentWalkThroughBinding

class WalkThroughFragment : Fragment() {

    private var _binding: FragmentWalkThroughBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_walk_through, container, false)
        val activity = (activity as MainActivity)
        activity.supportActionBar?.hide()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGetStarted.setOnClickListener {
            val activity = (activity as MainActivity)
            activity.supportActionBar?.show()
            findNavController().navigate(R.id.letYouInFragment)
//            findNavController().navigate(R.id.navigation_home)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}