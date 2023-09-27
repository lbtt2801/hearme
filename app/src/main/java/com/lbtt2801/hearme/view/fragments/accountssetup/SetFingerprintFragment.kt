package com.lbtt2801.hearme.view.fragments.accountssetup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentSetFingerprintBinding

class SetFingerprintFragment : Fragment() {
    private lateinit var binding: FragmentSetFingerprintBinding
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_set_fingerprint,
            container,
            false
        )
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = (activity as MainActivity)

        binding.btnSkip.setOnClickListener() {
            findNavController().navigate(R.id.action_setFingerprintFragment_to_followArtistsFragment)
        }

        binding.btnContinue.setOnClickListener() {
            findNavController().navigate(R.id.action_setFingerprintFragment_to_followArtistsFragment)
        }

        mainActivity.customToolbar(
            "VISIBLE", "Set Your Fingerprint", null, R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        )
        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }
    }
}