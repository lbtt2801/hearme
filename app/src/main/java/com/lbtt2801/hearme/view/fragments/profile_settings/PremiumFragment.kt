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
import com.lbtt2801.hearme.databinding.FragmentPremiumBinding

class PremiumFragment : Fragment() {
    private var _binding: FragmentPremiumBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity
    private var strCoin = ""
    private var strTime = ""
    val bundle = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_premium, container, false)

        mainActivity = (activity as MainActivity)

        mainActivity.customToolbar(
            "VISIBLE",
            "",
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

        binding.cardVip1.setOnClickListener {
            strCoin = binding.tvCoinVip1.text.toString()
            strTime = binding.tvTimeVip1.text.toString()

            bundle.putString("coin", strCoin)
            bundle.putString("time", strTime)
            bundle.putInt("background", R.color.orange)
            this.arguments = bundle

            findNavController().navigate(R.id.action_premiumFragment_to_paymentFragment, bundle)
        }

        binding.cardVip2.setOnClickListener {
            strCoin = binding.tvCoinVip2.text.toString()
            strTime = binding.tvTimeVip2.text.toString()

            bundle.putString("coin", strCoin)
            bundle.putString("time", strTime)
            bundle.putInt("background", R.color.purple)
            this.arguments = bundle

            findNavController().navigate(R.id.action_premiumFragment_to_paymentFragment, bundle)
        }

        binding.cardVip3.setOnClickListener {
            strCoin = binding.tvCoinVip3.text.toString()
            strTime = binding.tvTimeVip3.text.toString()

            bundle.putString("coin", strCoin)
            bundle.putString("time", strTime)
            bundle.putInt("background", R.color.red)
            this.arguments = bundle

            findNavController().navigate(R.id.action_premiumFragment_to_paymentFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}