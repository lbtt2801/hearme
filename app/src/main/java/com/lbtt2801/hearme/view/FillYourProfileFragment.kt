package com.lbtt2801.hearme.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentFillYourProfileBinding
import java.util.Calendar


class FillYourProfileFragment : Fragment() {
    private lateinit var binding: FragmentFillYourProfileBinding
    private lateinit var mainActivity: MainActivity

    private var isValidEmail = false
    private var isValidPhone = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_fill_your_profile,
            container,
            false
        )
        mainActivity = (activity as MainActivity)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ccp.registerCarrierNumberEditText(binding.edtPhoneNumber)
        binding.ccp.setPhoneNumberValidityChangeListener {
            isValidPhone = it
        }

        binding.edtDob.setOnClickListener() { calendarDialog(it) }

        binding.btnContinue.setOnClickListener() {
            isValidEmail = isValidEmail(binding.edtEmail.text)
            findNavController().navigate(R.id.action_fillYourProfileFragment_to_createNewPinFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        mainActivity.customToolbar(
            "VISIBLE", "Fill Your Profile", R.color.transparent,
            com.google.android.material.R.drawable.ic_arrow_back_black_24
        )
    }

    private fun calendarDialog(it: View?) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH) + 1
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dialog = DatePickerDialog(
            requireContext(),
            { _, mYear, mMonth, mDay ->
                binding.edtDob.setText("$mDay/$mMonth/$mYear")
            }, year, month, day
        )
        dialog.show()
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}