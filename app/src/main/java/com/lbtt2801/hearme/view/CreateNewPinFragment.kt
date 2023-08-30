package com.lbtt2801.hearme.view

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentCreateNewPinBinding

class CreateNewPinFragment : Fragment() {
    private lateinit var binding: FragmentCreateNewPinBinding

    private var numbersList = ArrayList<String>()
    private var passCode = ""
    private lateinit var num1: String
    private lateinit var num2: String
    private lateinit var num3: String
    private lateinit var num4: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_create_new_pin,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                Toast.makeText(requireContext(), "Valid PIN ($passCode)", Toast.LENGTH_SHORT).show()
            }
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
                    binding.edtNum2.setText(num1)
                }
                3 -> {
                    binding.edtNum2.isPressed = false
                    binding.edtNum3.isPressed = true
                    num3 = numbersList[2]
                    binding.edtNum3.setText(num1)
                }
                4 -> {
                    binding.edtNum3.isPressed = false
                    binding.edtNum4.isPressed = true
                    num4 = numbersList[3]
                    binding.edtNum4.setText(num1)
                    passCode = num1 + num2 + num3 + num4
                }
            }
        }
    }
}