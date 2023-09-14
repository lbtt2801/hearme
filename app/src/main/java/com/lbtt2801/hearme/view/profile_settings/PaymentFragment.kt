package com.lbtt2801.hearme.view.profile_settings

import android.R.attr.defaultValue
import android.R.attr.key
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.CardPaymentAdapter
import com.lbtt2801.hearme.databinding.FragmentPaymentBinding
import com.lbtt2801.hearme.model.CardPayment


class PaymentFragment : Fragment() {
    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity

    private var arrayList = ArrayList<CardPayment>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment, container, false)

        mainActivity = (activity as MainActivity)

        mainActivity.customToolbar(
            "VISIBLE",
            "Payment",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back),
            showIcMore = false,
            showIcFilter = false,
            showIcSearch = false,
            showIcNotification = false,
            showIcEdit = false,
            showIcScan = true
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
            val img = bundle.getInt("img")
            val name = bundle.getString("name", "**** **** **** ****")

            arrayList.add(CardPayment(img, name, true))
        }

        val adapter = CardPaymentAdapter(this, initData())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL))
        binding.recyclerView.adapter = adapter

        binding.btnAddNewCard.setOnClickListener {
            findNavController().navigate(R.id.action_paymentFragment_to_addNewCardFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        arrayList.clear()
        _binding = null
    }

    private fun initData(): ArrayList<CardPayment> {
        arrayList.add(
            CardPayment(R.drawable.paypal, "Paypal", true)
        )
        arrayList.add(
            CardPayment(R.drawable.google, "Google Pay", false)
        )
        arrayList.add(
            CardPayment(R.drawable.apple, "Apple Pay", false)
        )
        return arrayList
    }
}