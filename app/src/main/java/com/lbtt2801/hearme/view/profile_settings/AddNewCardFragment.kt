package com.lbtt2801.hearme.view.profile_settings

import android.R.attr.fragment
import android.R.attr.key
import android.R.attr.value
import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentAddNewCardBinding
import java.util.Calendar


class AddNewCardFragment : Fragment() {
    private var _binding: FragmentAddNewCardBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity
    var strNameCard = 15

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_new_card, container, false)

        mainActivity = (activity as MainActivity)

        mainActivity.customToolbar(
            "VISIBLE",
            "Add New Card",
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

//        binding.edtCardNumber.addTextChangedListener {
//            var s = it.toString()
//            if (s.length == 4 || s.length == 9 || s.length == 14)  {
//                s += " "
//                binding.edtCardNumber.setText(s)
//                binding.edtCardNumber.setSelection(s.lastIndex + 1)
//            } else if (s.length == 19)
//                mainActivity.hideKeyBoard()
//        }

        binding.edtCardNumber.addTextChangedListener { editable ->
            var str = editable.toString()
//            if (s.length == 4 || s.length == 9 || s.length == 14)  {
//                s += " "
//                binding.edtCardNumber.setText(s)
//                binding.edtCardNumber.setSelection(s.lastIndex + 1)
//            } else if (s.length == 19)
//                mainActivity.hideKeyBoard()

            for (i in 0 until strNameCard)
            { // 1234_1234_1234_1234
                if (str.length == 4 || str.length == 9 || str.length == 14) {
                    str += " "
                }

                str += '*'

            }
            strNameCard --
            binding.edtCardNumber.setText(str)


//            if (str.isNotEmpty()) {
//                str += "@"
//                binding.edtCardNumber.setText(str)
//                val strX = str.filter { it != '@' }
//                Log.v(TAG, "position: $strX")
//                binding.edtCardNumber.setSelection(0)
//            }

        }

//        binding.edtCardNumber.doAfterTextChanged { editable ->
//            var str = editable.toString()
//            if (str.isNotEmpty()) {
//                for (i in 0 until strNameCard)
//                { // 1234_1234_1234_1234
//                    if (str.length == 4 || str.length == 9 || str.length == 14) {
//                        str += " "
//                    }
//
//                    str += '*'
//
//                }
//                strNameCard --
////                this.str += "@"
////                Toast.makeText(context, "STR: $str - length: ${str.length}", Toast.LENGTH_SHORT).show()
//                binding.edtCardNumber.setText(str)
////                val strX = str.filter { it != '@' }
////                Log.v(TAG, "position: $strX")
////                binding.edtCardNumber.setSelection(0)
//            }
//        }



        binding.edtDob.setOnClickListener {
            mainActivity.hideKeyBoard()
            calendarDialog()
        }

        binding.edtCVV.addTextChangedListener {
            if (it.toString().length == 3)
                mainActivity.hideKeyBoard()
        }

        binding.btnAddNewCard.setOnClickListener {
            val strName = "**** **** **** *"
            val name = binding.edtCardNumber.text.toString().substring(16)
            val bundle = Bundle()
            bundle.putInt("img", R.drawable.mastercard)
            bundle.putString("name", strName.plus(name))
            this.arguments = bundle

            findNavController().navigate(R.id.action_addNewCardFragment_to_paymentFragment, bundle)
        }

    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
//            var s = s.toString()
//            if (s.length == 4 || s.length == 9 || s.length == 14)  {
//                s += " "
//                binding.edtCardNumber.setText(s)
//                binding.edtCardNumber.setSelection(s.lastIndex + 1)
//            } else if (s.length == 19)
//                mainActivity.hideKeyBoard()
            var str = s.toString()
            if (str.isNotEmpty()) {
                str += "@@"
                binding.edtCardNumber.setText(str)
                val strX = str.filter { it != '@' }
                binding.edtCardNumber.setSelection(strX.length)
            }
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }

    private fun calendarDialog() {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}