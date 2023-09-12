package com.lbtt2801.hearme.view.onboardingsignupsignin

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {

    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity
    private var handler: Handler = Handler()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_splash_screen, container, false)
        mainActivity = activity as MainActivity
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.visibility = View.VISIBLE

        handler.postDelayed({
            binding.progressBar.visibility = View.GONE
//            findNavController().navigate(R.id.walkThroughFragment)
            findNavController().navigate(R.id.signInFragment)

        }, 3000)
    }

    override fun onResume() {
        super.onResume()
        mainActivity.showBottomNav("gone")
        mainActivity.customToolbar("gone")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}