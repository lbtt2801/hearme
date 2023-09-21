package com.lbtt2801.hearme.view.fragments.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentViewDetailsProfileBinding
import com.lbtt2801.hearme.model.User
import com.lbtt2801.hearme.viewmodel.UserViewModel

class ViewDetailsProfileFragment : Fragment() {
    private lateinit var binding: FragmentViewDetailsProfileBinding
    private lateinit var mainActivity: MainActivity
    private var userEmail: String? = null
    private var user: User? = null

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_view_details_profile,
            container,
            false
        )
        mainActivity = activity as MainActivity
        userEmail = arguments?.getString("userEmail")
        user = userViewModel.lstDataUser.value?.first { it.email == userEmail }
        binding.user = user
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerFollow.setOnClickListener() {
            findNavController().navigate(
                R.id.action_viewDetailsProfileFragment_to_followerDetailFragment,
                Bundle().apply {
                    putString("emailID", userEmail)
                })
        }
    }

    override fun onResume() {
        super.onResume()
        mainActivity.showBottomNav("GONE")
        mainActivity.customToolbar(
            "VISIBLE",
            navIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back),
            showIcMore = true
        )
        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }
    }
}