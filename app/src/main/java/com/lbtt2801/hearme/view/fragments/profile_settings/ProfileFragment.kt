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
    private var fullName: String? = ""

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        mainActivity = activity as MainActivity
        mainActivity.customToolbar(
            "VISIBLE",
            "Profile",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.logo_nav),
            true
        )
        email = mainActivity.email
        binding.tvLanguage.text = mainActivity.language
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        avatar = userViewModel.lstDataUser.value?.first { it.email == email }?.avatar
        fullName = userViewModel.lstDataUser.value?.first { it.email == email }?.fullName

        binding.imgAvatar.background = avatar?.let { ContextCompat.getDrawable(requireContext(), it) }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}