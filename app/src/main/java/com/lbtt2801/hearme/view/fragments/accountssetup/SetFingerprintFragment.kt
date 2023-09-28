package com.lbtt2801.hearme.view.fragments.accountssetup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentSetFingerprintBinding
import java.util.concurrent.Executor

class SetFingerprintFragment : Fragment() {
    private lateinit var binding: FragmentSetFingerprintBinding
    private lateinit var mainActivity: MainActivity

//    private lateinit var executor: Executor
//    private lateinit var biometricPrompt: BiometricPrompt
//    private lateinit var promptInfo: PromptInfo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
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
        checkDeviceHasBiometric()

        binding.imageViewFingerPrint.setOnClickListener() {
        }

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

    private fun checkDeviceHasBiometric() {
        val biometricManager = BiometricManager.from(requireContext())
        when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                mainActivity.showSnack(requireView(), "App can use biometric")
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                mainActivity.showSnack(requireView(), "No biometric feature on this device")
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                mainActivity.showSnack(requireView(), "Biometric feature are currently unavailable")
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                mainActivity.showSnack(requireView(), "Device is not enable biometric feature")
            }
            else -> {
                mainActivity.showSnack(requireView(), "Something went wrong!")
            }
        }
    }
}