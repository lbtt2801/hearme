package com.lbtt2801.hearme.view.fragments.profile_settings

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
import com.lbtt2801.hearme.databinding.FragmentLanguageBinding

class LanguageFragment : Fragment() {
    private var _binding: FragmentLanguageBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_language, container, false)

        mainActivity = activity as MainActivity
        mainActivity.customToolbar(
            "VISIBLE",
            "Language",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        )

        mainActivity.showBottomNav("GONE")

        mainActivity.binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (mainActivity.language) {
            "English (US)" -> binding.rdoUS.isChecked = true
            "English (UK)" -> binding.rdoUK.isChecked = true
            "Mandarin" -> binding.rdoMandarin.isChecked = true
            "Hindi" -> binding.rdoHindi.isChecked = true
            "Spanish" -> binding.rdoSpanish.isChecked = true
            "French" -> binding.rdoFrench.isChecked = true
            "Arabic" -> binding.rdoArabic.isChecked = true
            "Bengali" -> binding.rdoBengali.isChecked = true
            "Russian" -> binding.rdoRussian.isChecked = true
            "Indonesia" -> binding.rdoIndonesia.isChecked = true
        }

        binding.rdoUS.setOnClickListener {
            binding.rdoUS.isChecked = true
            mainActivity.language = "English (US)"
        }

        binding.rdoUK.setOnClickListener {
            binding.rdoUK.isChecked = true
            mainActivity.language = "English (UK)"
        }

        binding.rdoMandarin.setOnClickListener {
            binding.rdoMandarin.isChecked = true
            mainActivity.language = "Mandarin"
        }

        binding.rdoHindi.setOnClickListener {
            binding.rdoHindi.isChecked = true
            mainActivity.language = "Hindi"
        }

        binding.rdoSpanish.setOnClickListener {
            binding.rdoSpanish.isChecked = true
            mainActivity.language = "Spanish"
        }

        binding.rdoFrench.setOnClickListener {
            binding.rdoFrench.isChecked = true
            mainActivity.language = "French"
        }

        binding.rdoArabic.setOnClickListener {
            binding.rdoArabic.isChecked = true
            mainActivity.language = "Arabic"
        }

        binding.rdoBengali.setOnClickListener {
            binding.rdoBengali.isChecked = true
            mainActivity.language = "Bengali"
        }

        binding.rdoRussian.setOnClickListener {
            binding.rdoRussian.isChecked = true
            mainActivity.language = "Russian"
        }

        binding.rdoIndonesia.setOnClickListener {
            binding.rdoIndonesia.isChecked = true
            mainActivity.language = "Indonesia"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}