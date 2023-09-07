package com.lbtt2801.hearme.view.accountssetup

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentFillYourProfileBinding
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
        mainActivity = (activity as MainActivity)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSkip.setOnClickListener() {
            findNavController().navigate(R.id.action_setFingerprintFragment_to_followArtistsFragment)
        }

        binding.btnContinue.setOnClickListener() {
            findNavController().navigate(R.id.action_setFingerprintFragment_to_followArtistsFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        mainActivity.customToolbar(
            "VISIBLE", "Set Your Fingerprint", R.color.transparent,
            R.drawable.ic_arrow_back
        )
        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }
    }
}