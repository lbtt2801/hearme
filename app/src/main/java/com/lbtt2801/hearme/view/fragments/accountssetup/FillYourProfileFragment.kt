package com.lbtt2801.hearme.view.fragments.accountssetup

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.net.ParseException
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentFillYourProfileBinding
import com.lbtt2801.hearme.viewmodel.UserViewModel
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import java.io.FileNotFoundException
import java.text.SimpleDateFormat
import java.util.*


class FillYourProfileFragment : Fragment() {
    private lateinit var binding: FragmentFillYourProfileBinding
    private lateinit var mainActivity: MainActivity
    private var email: String? = null
    private var avatarUri: Uri? = null

    private val userViewModel: UserViewModel by activityViewModels()

    private var isValidEmail = false
    private var isValidPhone = false

    private val changeImage =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                avatarUri = data?.data
                Picasso.get().load(avatarUri).transform(CropCircleTransformation())
                    .placeholder(R.drawable.progress_icon)
                    .error(R.drawable.ellipse).fit().centerCrop().into(binding.imageViewAvatar)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_fill_your_profile,
            container,
            false
        )
        mainActivity = (activity as MainActivity)
        email = mainActivity.email
        Toast.makeText(requireContext(), "$email", Toast.LENGTH_SHORT).show()
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonPhotoPicker.setOnClickListener() {
            val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            changeImage.launch(pickImg)
        }

        binding.ccp.registerCarrierNumberEditText(binding.edtPhoneNumber)
        binding.ccp.setPhoneNumberValidityChangeListener {
            isValidPhone = it
        }

        binding.edtDob.setOnClickListener() { calendarDialog(it) }

        binding.btnSkip.setOnClickListener() {
            findNavController().navigate(
                R.id.action_fillYourProfileFragment_to_createNewPinFragment
            )
        }

        binding.btnContinue.setOnClickListener() {
            isValidEmail = isValidEmail(binding.edtEmail.text)
            if (binding.edtFullName.text.isEmpty() || binding.edtNickName.text.isEmpty() || binding.edtDob.text.isEmpty() || binding.edtEmail.text.isEmpty() || binding.edtPhoneNumber.text.isEmpty()) {
                mainActivity.showSnack(view, "Enter full information, please!")
            } else {
                if (!isValidEmail || !isValidPhone) {
                    mainActivity.showSnack(view, "Invalid Email or Phone!")
                } else {
                    val phone = "${binding.edtPhoneNumber.text}"
//                        "${binding.ccp.selectedCountryCodeAsInt} ${binding.edtPhoneNumber.text}"

                    //Còn lỗi
//                    val drawableId: Int = binding.imageViewAvatar.tag.toString().toInt()

                    email?.let {
                        stringToDate(binding.edtDob.text.toString())?.let { it1 ->
                            userViewModel.updateUserInfo(
                                it,
                                binding.edtFullName.text.toString(),
                                binding.edtNickName.text.toString(),
                                it1,
                                binding.edtEmail.text.toString(),
                                phone,
                                avatarUri
                            )
                        }
                    }
                    Log.v(TAG, userViewModel.lstDataUser.value?.size.toString())
                    findNavController().navigate(
                        R.id.action_fillYourProfileFragment_to_createNewPinFragment
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mainActivity.customToolbar(
            "VISIBLE", "Fill Your Profile", null, R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        )
        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }
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

    fun decodeUriAsBitmap(context: Context, uri: Uri?): Bitmap? {
        var bitmap: Bitmap? = null //from   w  ww  . j  a  v  a2s.  c om
        bitmap = try {
            BitmapFactory.decodeStream(
                uri?.let {
                    context
                        .contentResolver.openInputStream(it)
                }
            )
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return null
        }
        return bitmap
    }
}