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
import com.lbtt2801.hearme.data.UserData
import com.lbtt2801.hearme.databinding.FragmentSignUpBinding
import com.lbtt2801.hearme.model.User
import com.lbtt2801.hearme.viewmodel.SignUpViewModel
import java.util.Date

class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy {
        ViewModelProvider(this)[SignUpViewModel::class.java]
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

        Toast.makeText(context, "Size: " + UserData.data().size.toString(), Toast.LENGTH_SHORT).show()

//        UserData.data().add(User("lbb","123", R.drawable.logo,"45","54", Date(2002, 12, 6, 0, 0, 0),"VN","55",2345,38,32,false))

        viewModel.lstDataUser.observe(viewLifecycleOwner) {
//            UserData.data().clear()
//            UserData.data().addAll(it)
            if (it.isEmpty())
                Toast.makeText(context, "list is null or empty", Toast.LENGTH_SHORT).show()
            else Toast.makeText(context, "Size XXX: " + UserData.data().size.toString(), Toast.LENGTH_SHORT).show()
        }

//        viewModel.addDataUser(binding.edtEmail.text.toString(), binding.edtPass.text.toString())
        viewModel.addUser(binding.edtEmail.text.toString(), binding.edtPass.text.toString())

        binding.edtEmail.setOnFocusChangeListener  { _, hasFocus ->
            val color = if (hasFocus) resources.getColor(R.color.btn) else Color.BLACK
            binding.txtLayoutEmail.setStartIconTintList(ColorStateList.valueOf(color))
        }

        binding.edtPass.setOnFocusChangeListener  { _, hasFocus ->
            val color = if (hasFocus) resources.getColor(R.color.btn) else Color.BLACK
            binding.txtLayoutPass.setStartIconTintList(ColorStateList.valueOf(color))
            binding.txtLayoutPass.setEndIconTintList(ColorStateList.valueOf(color))
        }

        binding.tvSignIn.setOnClickListener() {
            findNavController().run {
                popBackStack()
                navigate(R.id.signInFragment)
            }
        }

        binding.btnSignUp.setOnClickListener() {

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