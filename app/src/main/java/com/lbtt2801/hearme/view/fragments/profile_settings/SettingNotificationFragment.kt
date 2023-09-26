package com.lbtt2801.hearme.view.fragments.profile_settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentSettingNotificationBinding

class SettingNotificationFragment : Fragment() {
    private var _binding: FragmentSettingNotificationBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_setting_notification,
            container,
            false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).customToolbar(
            "VISIBLE",
            "Notification",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        )

        (activity as MainActivity).showBottomNav("GONE")

        (activity as MainActivity).binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}