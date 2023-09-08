package com.lbtt2801.hearme.view

import android.content.ContentValues.TAG
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentSignInBinding
import com.lbtt2801.hearme.model.User
import com.lbtt2801.hearme.viewmodel.EmailViewModel
import com.lbtt2801.hearme.viewmodel.UserViewModel


class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private lateinit var mainActivity: MainActivity
    private val binding get() = _binding!!
    private lateinit var lstDataUser: List<User>

    private val viewModelUser: UserViewModel by activityViewModels()

//    private val viewModel by lazy {
//        ViewModelProvider(this)[UserViewModel::class.java]
//    }

    private val emailViewModel: EmailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        mainActivity = activity as MainActivity
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelUser.lstDataUser.observe(viewLifecycleOwner) {
            Log.v(TAG, "SignIn -> ${it.size}")
        }

        viewModelUser.lstDataUser.observe(viewLifecycleOwner, Observer {
            lstDataUser = it
            Log.v(TAG, "${it.size}")
            if (lstDataUser.isNullOrEmpty())
                Toast.makeText(context, "list is null or empty", Toast.LENGTH_SHORT).show()
        })

        (activity as MainActivity).binding.toolBar.setNavigationOnClickListener() {
            findNavController().navigate(R.id.letYouInFragment)
        }

        binding.edtEmail.setOnFocusChangeListener { _, hasFocus ->
            val color = if (hasFocus) resources.getColor(R.color.bg_button) else Color.BLACK
            binding.txtLayoutEmail.setStartIconTintList(ColorStateList.valueOf(color))
            binding.txtLayoutEmail.error = ""
        }

        binding.edtPass.setOnFocusChangeListener { _, hasFocus ->
            val color = if (hasFocus) resources.getColor(R.color.bg_button) else Color.BLACK
            binding.txtLayoutPass.setStartIconTintList(ColorStateList.valueOf(color))
            binding.txtLayoutPass.setEndIconTintList(ColorStateList.valueOf(color))

            if (binding.edtPass.text?.length!! >= 6)
                binding.txtLayoutPass.error = ""
        }

        binding.tvSignUp.setOnClickListener() {
            findNavController().navigate(R.id.signUpFragment)
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
                val kq =
                    lstDataUser.filter { it.email == binding.edtEmail.text.toString() && it.password == binding.edtPass.text.toString() }
                if (kq.isNotEmpty()) {
                    Toast.makeText(context, "Welcome to Hearme!", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.navigation_home)
                    emailViewModel.selectItem(email)
                } else {
                    Toast.makeText(
                        context,
                        "Please check Email or Password !!!",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.edtEmail.requestFocus()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.tvForgot.setOnClickListener() {
            val email = binding.edtEmail.text.toString()
            if (email.isEmpty()) {
                mainActivity.showSnack(requireView(), "Must to input email for finding password!")
            } else if (lstDataUser.any { it.email == email }) {
                findNavController().navigate(R.id.action_signInFragment_to_selectMethodFragment)
                emailViewModel.selectItem(binding.edtEmail.text.toString())
            } else {
                mainActivity.showSnack(requireView(), "The email is not exists!")
            }
        }
        mainActivity.showBottomNav("gone")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}