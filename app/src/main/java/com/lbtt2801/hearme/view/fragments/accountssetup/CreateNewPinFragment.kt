package com.lbtt2801.hearme.view.fragments.accountssetup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentCreateNewPinBinding
import com.lbtt2801.hearme.viewmodel.UserViewModel

class CreateNewPinFragment : Fragment() {
    private lateinit var binding: FragmentCreateNewPinBinding
    private lateinit var mainActivity: MainActivity
    private val viewModelUser: UserViewModel by activityViewModels()
    private var email: String? = null

    private var numbersList = ArrayList<String>()
    private var passCode = ""
    private lateinit var num1: String
    private lateinit var num2: String
    private lateinit var num3: String
    private lateinit var num4: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_create_new_pin,
                container,
                false
            )
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = activity as MainActivity
        email = mainActivity.email

        binding.edtNum1.isEnabled = false
        binding.edtNum2.isEnabled = false
        binding.edtNum3.isEnabled = false
        binding.edtNum4.isEnabled = false

        binding.btnNumber0.setOnClickListener() {
            numbersList.add("0")
            passNumber(numbersList)
        }
        binding.btnNumber1.setOnClickListener() {
            numbersList.add("1")
            passNumber(numbersList)
        }
        binding.btnNumber2.setOnClickListener() {
            numbersList.add("2")
            passNumber(numbersList)
        }
        binding.btnNumber3.setOnClickListener() {
            numbersList.add("3")
            passNumber(numbersList)
        }
        binding.btnNumber4.setOnClickListener() {
            numbersList.add("4")
            passNumber(numbersList)
        }
        binding.btnNumber5.setOnClickListener() {
            numbersList.add("5")
            passNumber(numbersList)
        }
        binding.btnNumber6.setOnClickListener() {
            numbersList.add("6")
            passNumber(numbersList)
        }
        binding.btnNumber7.setOnClickListener() {
            numbersList.add("7")
            passNumber(numbersList)
        }
        binding.btnNumber8.setOnClickListener() {
            numbersList.add("8")
            passNumber(numbersList)
        }
        binding.btnNumber9.setOnClickListener() {
            numbersList.add("9")
            passNumber(numbersList)
        }
        binding.btnNumberClear.setOnClickListener() {
            if (binding.edtNum4.text.isNotEmpty()) {
                numbersList.removeAt(3)
                binding.edtNum4.text.clear()
                binding.edtNum4.isPressed = false
            } else if (binding.edtNum3.text.isNotEmpty()) {
                numbersList.removeAt(2)
                binding.edtNum3.text.clear()
                binding.edtNum3.isPressed = false
            } else if (binding.edtNum2.text.isNotEmpty()) {
                numbersList.removeAt(1)
                binding.edtNum2.text.clear()
                binding.edtNum2.isPressed = false
            } else if (binding.edtNum1.text.isNotEmpty()) {
                numbersList.removeAt(0)
                binding.edtNum1.text.clear()
                binding.edtNum1.isPressed = false
            }
            passNumber(numbersList)
        }

        binding.btnContinue.setOnClickListener() {
            if (passCode.length < 4) {
                Toast.makeText(requireContext(), "Invalid PIN!", Toast.LENGTH_SHORT).show()
            } else {
                email?.let { it1 -> viewModelUser.updateUserPin(it1, passCode.toInt()) }
                email?.let { it1 -> viewModelUser.updateIsFirstLogin(it1, false) }
                findNavController().navigate(
                    R.id.action_createNewPinFragment_to_setFingerprintFragment)
            }
        }

        mainActivity.customToolbar(
            "VISIBLE", "Create New PIN", null, R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        )
        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }
    }

    private fun passNumber(numbersList: ArrayList<String>) {
        if (numbersList.size != 0) {
            when (numbersList.size) {
                1 -> {
                    binding.edtNum1.isPressed = true
                    num1 = numbersList[0]
                    binding.edtNum1.setText(num1)
                }
                2 -> {
                    binding.edtNum1.isPressed = false
                    binding.edtNum2.isPressed = true
                    num2 = numbersList[1]
                    binding.edtNum2.setText(num2)
                }
                3 -> {
                    binding.edtNum2.isPressed = false
                    binding.edtNum3.isPressed = true
                    num3 = numbersList[2]
                    binding.edtNum3.setText(num3)
                }
                4 -> {
                    binding.edtNum3.isPressed = false
                    binding.edtNum4.isPressed = true
                    num4 = numbersList[3]
                    binding.edtNum4.setText(num4)
                    passCode = num1 + num2 + num3 + num4
                }
            }
        }
    }
}