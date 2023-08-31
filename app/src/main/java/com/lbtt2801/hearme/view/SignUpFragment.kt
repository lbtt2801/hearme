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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.UserData
import com.lbtt2801.hearme.databinding.FragmentSignUpBinding
import com.lbtt2801.hearme.viewmodel.UserViewModel

class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        binding.tvSignIn.setOnClickListener() {
            findNavController().run {
                popBackStack()
                navigate(R.id.signInFragment)
            }
        }

        binding.btnSignUp.setOnClickListener() {
            val email = binding.edtEmail.text.toString().trim()
            val pass = binding.edtPass.text.toString().trim()
            val sizeUserDataOld = UserData.data().size
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

            // kiem tra trung lap email
            if (viewModel.checkDuplicateEmails(email)) {
                binding.edtEmail.text = null
                binding.edtEmail.requestFocus()
                checkEmail = false
                binding.txtLayoutEmail.error = "Email Already Exists !!"
            }

            if (checkEmail && checkPass) {
                viewModel.lstDataUser.observe(viewLifecycleOwner) {
                    if (it.size > sizeUserDataOld){
                        Toast.makeText(context, "Sign Up Success", Toast.LENGTH_SHORT).show()
                        findNavController().run {
                            popBackStack()
                            navigate(R.id.signInFragment)
                        }
                    }
                    else Toast.makeText(context, "Fail Fail Fail", Toast.LENGTH_SHORT).show()
                }
                viewModel.addDataUser(email, pass)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}