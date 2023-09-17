package com.lbtt2801.hearme.view.fragments.library

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.MusicAdapter
import com.lbtt2801.hearme.databinding.FragmentPlayListBinding
import com.lbtt2801.hearme.viewmodel.MusicViewModel

class PlayListFragment : Fragment() {
    private var _binding: FragmentPlayListBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity
    private lateinit var musicAdapter: MusicAdapter

    private val musicViewModel: MusicViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_play_list, container, false)

        mainActivity = (activity as MainActivity)

        mainActivity.customToolbar(
            "VISIBLE",
            "Playlists",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back),
            showIcMore = true,
            showIcFilter = false,
            showIcSearch = true
        )
        mainActivity.showBottomNav("GONE")
        mainActivity.binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.btnAddNewPlayList.setOnClickListener {
            showDialogBottom()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showDialogBottom() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_add_playlist)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations =
            com.google.android.material.R.style.MaterialAlertDialog_Material3_Animation
        dialog.window?.setGravity(Gravity.BOTTOM)
        dialog.show()

        val btnYes: Button = dialog.findViewById(R.id.btnYes)
        val btnNo: Button = dialog.findViewById(R.id.btnCancel)

        btnYes.setOnClickListener {

            dialog.dismiss()
        }

        btnNo.setOnClickListener {
            dialog.dismiss()
        }
    }
}