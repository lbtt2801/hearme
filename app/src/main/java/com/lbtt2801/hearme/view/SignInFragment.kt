package com.lbtt2801.hearme.view

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

        binding.tvSignUp.setOnClickListener() {
            findNavController().run {
                popBackStack()
                navigate(R.id.signUpFragment)
            }
        }

        binding.btnSignIn.setOnClickListener() {
            val kq = lstDataUser?.filter { it.email == binding.edtEmail.text.toString() && it.password == binding.edtPass.text.toString() }
            if (kq!!.isNotEmpty()) {
                Toast.makeText(context, "Welcome to Hearme!", Toast.LENGTH_SHORT).show()
                findNavController().run {
                    popBackStack()
                    navigate(R.id.fillYourProfileFragment)
                }
            } else  Toast.makeText(context, "Please check Email or Password !!!", Toast.LENGTH_SHORT).show()


        }

        (activity as MainActivity).binding.toolBar.setNavigationOnClickListener() {
            findNavController().run {
                popBackStack()
                navigate(R.id.letYouInFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}