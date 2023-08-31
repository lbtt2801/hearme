package com.lbtt2801.hearme.view

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentSignInBinding
import com.lbtt2801.hearme.model.User
import com.lbtt2801.hearme.viewmodel.SignInViewModel


class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private var lstDataUser: List<User> ?= null
    private val signInViewModel by lazy {
        ViewModelProvider(this)[SignInViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signInViewModel.lstDataUser.observe(viewLifecycleOwner) {
            lstDataUser = it
            if (lstDataUser.isNullOrEmpty())
                Toast.makeText(context, "list is null or empty", Toast.LENGTH_SHORT).show()
        }

        signInViewModel.getListDataUser()

        (activity as MainActivity).binding.toolBar.setNavigationOnClickListener() {
            findNavController().run {
                popBackStack()
                navigate(R.id.letYouInFragment)
            }
        }

        binding.edtEmail.setOnFocusChangeListener  { _, hasFocus ->
            val color = if (hasFocus) resources.getColor(R.color.bg_button) else Color.BLACK
            binding.txtLayoutEmail.setStartIconTintList(ColorStateList.valueOf(color))
            binding.txtLayoutEmail.error = ""
        }

        binding.edtPass.setOnFocusChangeListener  { _, hasFocus ->
            val color = if (hasFocus) resources.getColor(R.color.bg_button) else Color.BLACK
            binding.txtLayoutPass.setStartIconTintList(ColorStateList.valueOf(color))
            binding.txtLayoutPass.setEndIconTintList(ColorStateList.valueOf(color))

            if (binding.edtPass.text?.length!! >= 6)
                binding.txtLayoutPass.error = ""
        }

        binding.tvSignUp.setOnClickListener() {
            findNavController().run {
                popBackStack()
                navigate(R.id.signUpFragment)
            }
        }

        binding.btnSignIn.setOnClickListener() {
            val email = binding.edtEmail.text.toString().trim()
            val pass = binding.edtPass.text.toString().trim()
            var checkEmail = false
            var checkPass = false

            // kiem tra dinh dang email va do dai pass
            val pattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,18}".toRegex()
            repeat(email.length) {
                if (pattern.matches(email))
                    checkEmail = true
                else binding.txtLayoutEmail.error = "Please enter the correct email format"
            }

            if (pass.length >= 6)
                checkPass = true
            else binding.txtLayoutPass.error = "Password length must be >= 6"

            if (checkEmail && checkPass) {
                val kq = lstDataUser?.filter { it.email == binding.edtEmail.text.toString() && it.password == binding.edtPass.text.toString() }
                if (kq!!.isNotEmpty()) {
                    Toast.makeText(context, "Welcome to Hearme!", Toast.LENGTH_SHORT).show()
                    findNavController().run {
                        popBackStack()
                        navigate(R.id.navigation_home)
                    }
                } else {
                    Toast.makeText(context, "Please check Email or Password !!!", Toast.LENGTH_SHORT).show()
                    binding.edtEmail.requestFocus()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}