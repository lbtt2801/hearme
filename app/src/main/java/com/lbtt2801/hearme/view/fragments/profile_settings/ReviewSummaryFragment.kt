package com.lbtt2801.hearme.view.fragments.profile_settings

import android.app.Dialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentReviewSummaryBinding
import com.lbtt2801.hearme.view.dialogs.AuthorizationProgressDialog
import kotlin.system.exitProcess

class ReviewSummaryFragment : Fragment() {
    private var _binding: FragmentReviewSummaryBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity
    private var strCoin = ""
    private var strTime = ""
    private var intLogo = -1
    private var strNameCard = ""
    private var intBackground = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_review_summary, container, false)

        mainActivity = (activity as MainActivity)

        mainActivity.customToolbar(
            "VISIBLE",
            "Review Summary",
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

        val bundle = this.arguments
        if (bundle != null) {
            intBackground = bundle.getInt("background", R.color.transparent)
            strCoin = bundle.getString("coin", "error")
            strTime = bundle.getString("time", "error")
            intLogo = bundle.getInt("imgCard", R.drawable.mastercard)
            strNameCard = bundle.getString("nameCard", "error")
        }

        binding.cardVip.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), intBackground))
        binding.tvExpense.text = "$strCoin"
        binding.tvTime.text = strTime
        binding.tvCoinAmount.text = "$strCoin"
        val total: Double = strCoin.substring(1).toDouble() + binding.tvCoinTax.text.toString().substring(1).toDouble()
        binding.tvCoinTotal.text = String.format("$%.2f", total)
        binding.imgCard.setImageResource(intLogo)
        binding.tvNameCard.text = strNameCard

        binding.btnContinue.setOnClickListener {
            showDialogBox()
        }

        binding.tvChange.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showDialogBox() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_success)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        val btnYes: TextView = dialog.findViewById(R.id.btnYes)
        btnYes.setOnClickListener {
            dialog.dismiss()
        }
    }
}