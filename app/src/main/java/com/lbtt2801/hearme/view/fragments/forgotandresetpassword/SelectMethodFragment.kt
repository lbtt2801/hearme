package com.lbtt2801.hearme.view.fragments.forgotandresetpassword

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
import com.lbtt2801.hearme.databinding.FragmentSelectMethodBinding
import com.lbtt2801.hearme.viewmodel.UserViewModel

class SelectMethodFragment : Fragment() {
    private lateinit var binding: FragmentSelectMethodBinding
    private lateinit var mainActivity: MainActivity

    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_select_method,
                container,
                false
            )
        mainActivity = activity as MainActivity
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.lstDataUser.value?.first { it.email == mainActivity.email }.apply {
            binding.textPhone.text = "+${mainActivity.protectedPhone(this?.phone.toString())}"
            binding.textEmail.text = mainActivity.protectedEmail(this?.email.toString())
        }

        binding.btnSMS.setOnCheckedChangeListener() { button, isChecked ->
            if (isChecked) {
                button.isChecked = true
                binding.btnEmail.isChecked = false
            } else {
                button.isChecked = false
            }
        }

        binding.btnEmail.setOnCheckedChangeListener() { button, isChecked ->
            if (isChecked) {
                button.isChecked = true
                binding.btnSMS.isChecked = false
            } else {
                button.isChecked = false
            }
        }

        binding.btnContinue.setOnClickListener() {
            val method = if (binding.btnSMS.isChecked) {
                binding.textPhone.text.toString()
            } else if (binding.btnEmail.isChecked) {
                binding.textEmail.text.toString()
            } else {
                mainActivity.showSnack(view, "Please, let's choose one method!")
                return@setOnClickListener
            }
            findNavController().navigate(
                R.id.action_selectMethodFragment_to_fillOTPFragment,
                Bundle().apply {
                    putString("method", method)
                })
        }
    }

    override fun onResume() {
        super.onResume()
        mainActivity.customToolbar(
            "VISIBLE", "Forgot Password", null, R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        )
        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }
    }
}