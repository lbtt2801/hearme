package com.lbtt2801.hearme.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentTopItemBinding

class TopItemFragment : Fragment() {
    private var _binding: FragmentTopItemBinding?= null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_item, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = requireArguments()

        binding.tvInCard.text = bundle.getString("tvInCard")
        binding.tvTitle.text = bundle.getString("tvTitle")
        binding.tvDetail.text = bundle.getString("tvDetail")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}