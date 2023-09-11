package com.lbtt2801.hearme.view.profile_settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.MusicAdapter
import com.lbtt2801.hearme.databinding.FragmentProfileDetailBinding
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.viewmodel.UserViewModel

class ProfileDetailFragment : Fragment() {
    private var _binding: FragmentProfileDetailBinding? = null
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

        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_detail, container, false)
        mainActivity = activity as MainActivity
        email = mainActivity.email

        mainActivity.customToolbar(
            "VISIBLE",
            "Profile",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back),
            showIcMore = false,
            showIcFilter = false,
            showIcSearch = false,
            showIcNotification = false,
            showIcEdit = true
        )

        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        avatar = userViewModel.lstDataUser.value?.first { it.email == email }?.avatar
        fullName = userViewModel.lstDataUser.value?.first { it.email == email }?.fullName
        val follower = userViewModel.lstDataUser.value?.first { it.email == email }?.listFollowers
        val following = userViewModel.lstDataUser.value?.first { it.email == email }?.listArtistsFollowing
        val lstData = userViewModel.lstDataUser.value?.first { it.email == email }?.listPlayedMusic

        binding.imgAvatar.background = avatar?.let { ContextCompat.getDrawable(requireContext(), it) }
        binding.tvNameUser.text = fullName.toString()
        binding.numberFollowers.text = follower?.size.toString()
        binding.numberFollowing.text = following?.size.toString()

        binding.btnEdit.setOnClickListener {
            findNavController().navigate(R.id.action_profileDetailFragment_to_editProfileFragment)
        }

        lstData?.let { displayRecyclerView(it) }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun displayRecyclerView(lstData: ArrayList<Music>) {
        val layoutRecyclerView = GridLayoutManager(view?.context, 2, LinearLayoutManager.VERTICAL, false)
        val musicAdapter = MusicAdapter(lstData, 0)
        binding.recyclerView.apply {
            layoutManager = layoutRecyclerView
            adapter = musicAdapter
        }
    }

}