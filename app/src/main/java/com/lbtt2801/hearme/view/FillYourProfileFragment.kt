package com.lbtt2801.hearme.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.ParseException
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentFillYourProfileBinding
import com.lbtt2801.hearme.viewmodel.UserViewModel
import java.text.SimpleDateFormat
import java.util.*


class FillYourProfileFragment : Fragment() {
    private lateinit var binding: FragmentFillYourProfileBinding
    private lateinit var mainActivity: MainActivity
    private var email: String? = null

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
        email = arguments?.getString("email").toString()
        Toast.makeText(requireContext(), "$email", Toast.LENGTH_SHORT).show()
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProvider(this)[UserViewModel::class.java]

        binding.ccp.registerCarrierNumberEditText(binding.edtPhoneNumber)
        binding.ccp.setPhoneNumberValidityChangeListener {
            isValidPhone = it
        }

        binding.edtDob.setOnClickListener() { calendarDialog(it) }

        binding.btnContinue.setOnClickListener() {
            isValidEmail = isValidEmail(binding.edtEmail.text)
            if (binding.edtFullName.text.isEmpty() || binding.edtNickName.text.isEmpty() || binding.edtDob.text.isEmpty() || binding.edtEmail.text.isEmpty() || binding.edtPhoneNumber.text.isEmpty()) {
                val snack = Snackbar.make(
                    view,
                    "Enter full information!",
                    Snackbar.LENGTH_LONG
                )
                snack.show()
            } else {
                if (!isValidEmail || !isValidPhone) {
                    val snack = Snackbar.make(
                        view,
                        "Invalid Email or Phone!",
                        Snackbar.LENGTH_LONG
                    )
                    snack.show()
                } else {
                    email?.let {
                        stringToDate(binding.edtDob.text.toString())?.let { it1 ->
                            model.updateUserInfo(
                                it,
                                binding.edtFullName.text.toString(),
                                binding.edtNickName.text.toString(),
                                it1,
                                binding.edtEmail.text.toString(),
                                binding.edtPhoneNumber.text.toString()
                            )
                        }
                    }
                    Log.v(TAG, model.lstDataUser.value.toString())
                    findNavController().navigate(
                        R.id.action_fillYourProfileFragment_to_createNewPinFragment,
                        Bundle().apply {
                            putString("email", email)
                        })
                }
            }
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

    fun stringToDate(date: String): Date? {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        var dateConverted: Date? = null
        try {
            dateConverted = dateFormat.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return dateConverted
    }
}