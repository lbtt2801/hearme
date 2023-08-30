package com.lbtt2801.hearme.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentLetYouInBinding
import com.lbtt2801.hearme.databinding.FragmentWalkThroughBinding

class LetYouInFragment : Fragment() {
    private var _binding: FragmentLetYouInBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_let_you_in, container, false)
        val activity = (activity as MainActivity)
        activity.customToolbar("VISIBLE","", R.color.transparent, R.drawable.ic_arrow_back)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.signInFragment)
        }

        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.tvSignUp.setOnClickListener { onClickSignUp(it) }
    }

    private fun onClickSignUp(it: View?) {
        findNavController().navigate(R.id.action_letYouInFragment_to_signUpFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}