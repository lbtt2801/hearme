package com.lbtt2801.hearme.view.fragments.profile_settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentProfileBinding
import com.lbtt2801.hearme.viewmodel.UserViewModel

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity
    private var email: String? = ""
    private var avatar: Int? = null
    private var avatarUrl: String? = null
    private var fullName: String? = ""

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = activity as MainActivity
        email = mainActivity.email
        binding.tvLanguage.text = mainActivity.language
        mainActivity.customToolbar(
            "VISIBLE",
            "Profile",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.logo_nav),
            true
        )
        mainActivity.showBottomNav("VISIBLE")

        avatar = userViewModel.lstDataUser.value?.first { it.email == email }?.avatar
        avatarUrl = userViewModel.lstDataUser.value?.first { it.email == email }?.avatarUrl
        fullName = userViewModel.lstDataUser.value?.first { it.email == email }?.fullName

        val options = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.progressbar)
            .error(R.drawable.ellipse)
        if (avatar != null)
            binding.imgAvatar.background =
                avatar?.let { ContextCompat.getDrawable(requireContext(), it) }
        else if (avatarUrl != null)
            Glide.with(this).load(avatarUrl).apply(options).into(binding.imgAvatar)
        else
            binding.imgAvatar.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.ellipse)
        binding.tvNameUser.text = fullName
        binding.tvEmailUser.text = email

        binding.btnGetPremium.setOnClickListener {
            findNavController().navigate(R.id.action_item_nav_profile_to_premiumFragment)
        }

        binding.linearProfile.setOnClickListener {
            findNavController().navigate(R.id.action_item_nav_profile_to_profileDetailFragment)
        }
        binding.linearNotification.setOnClickListener {
            findNavController().navigate(R.id.action_item_nav_profile_to_settingNotificationFragment)
        }
        binding.linearAudioVideo.setOnClickListener {
            findNavController().navigate(R.id.action_item_nav_profile_to_audioVideoFragment)
        }
        binding.linearPlayback.setOnClickListener {
            findNavController().navigate(R.id.action_item_nav_profile_to_settingPlaybackFragment)
        }
        binding.linearDataSaver.setOnClickListener {
            findNavController().navigate(R.id.action_item_nav_profile_to_dataSaverStorageFragment)
        }
        binding.linearSecurity.setOnClickListener {
            findNavController().navigate(R.id.action_item_nav_profile_to_securityFragment)
        }
        binding.linearLanguage.setOnClickListener {
            findNavController().navigate(R.id.action_item_nav_profile_to_languageFragment)
        }
        binding.linearDarkMode.setOnClickListener {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}