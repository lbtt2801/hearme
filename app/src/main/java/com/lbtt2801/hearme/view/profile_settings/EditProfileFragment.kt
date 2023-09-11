package com.lbtt2801.hearme.view.profile_settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentEditProfileBinding
import com.lbtt2801.hearme.viewmodel.UserViewModel
import java.text.SimpleDateFormat

class EditProfileFragment : Fragment() {
    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity
    private var email: String? = ""
    private var fullName: String? = ""

    private val userViewModel: UserViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_profile, container, false)

        mainActivity = activity as MainActivity
        email = mainActivity.email

        mainActivity.customToolbar(
            "VISIBLE",
            "Edit Profile",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        )

        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val formatter = SimpleDateFormat("dd/MM/yyyy")
        fullName = userViewModel.lstDataUser.value?.first { it.email == email }?.fullName
        val nickName = userViewModel.lstDataUser.value?.first { it.email == email }?.nickName
        val dob = userViewModel.lstDataUser.value?.first { it.email == email }?.dob
        val phone = userViewModel.lstDataUser.value?.first { it.email == email }?.phone
        val gender = userViewModel.lstDataUser.value?.first { it.email == email }?.gender

        binding.edtFullName.setText(fullName, TextView.BufferType.EDITABLE)
        binding.edtNickName.setText(nickName, TextView.BufferType.EDITABLE)
        binding.edtDob.setText(dob?.let { formatter.format(it) }, TextView.BufferType.EDITABLE)
        binding.edtEmail.setText(email, TextView.BufferType.EDITABLE)
        binding.edtPhoneNumber.setText(phone, TextView.BufferType.EDITABLE)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}