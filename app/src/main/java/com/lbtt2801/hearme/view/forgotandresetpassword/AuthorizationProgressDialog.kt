package com.lbtt2801.hearme.view.forgotandresetpassword

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lbtt2801.hearme.R

class AuthorizationProgressDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val handler = Handler()
        val builder =
            MaterialAlertDialogBuilder(requireContext(), R.style.MaterialAlertDialog_rounded)
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_progress, null)
        builder.setView(view)

        handler.postDelayed({
            findNavController().navigate(CreateNewPasswordFragmentDirections.actionCreateNewPasswordFragmentToNavigationHome())
        }, 2000)
        return builder.create()
    }

    companion object {
        const val TAG = "AuthorizationProgressDialog"
    }
}