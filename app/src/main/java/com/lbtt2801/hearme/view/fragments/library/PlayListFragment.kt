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
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.PlaylistAdapter
import com.lbtt2801.hearme.databinding.FragmentPlayListBinding
import com.lbtt2801.hearme.model.Playlist
import com.lbtt2801.hearme.viewmodel.HomeViewModel
import com.lbtt2801.hearme.viewmodel.UserViewModel

class PlayListFragment : Fragment() {
    private var _binding: FragmentPlayListBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity
    private lateinit var playlistAdapter: PlaylistAdapter
    private var lst = ArrayList<Playlist>()
    private var email: String = ""
    private var spinnerItems = arrayOf("Recently Added", "Added Before")
    private var check = false

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_play_list, container, false)

        mainActivity = (activity as MainActivity)
        email = mainActivity.email

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

        userViewModel.lstDataPlaylist.observe((activity as MainActivity), Observer {
            lst = it as ArrayList<Playlist>
            var lstMain = lst.reversed() as ArrayList<Playlist>

            binding.spinner.setSelection(0)
            binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (p2 == 0) {
                        if (check)
                            lstMain = lst.reversed() as ArrayList<Playlist>
                        displayRecyclerView(lstMain)
                    } else {
                        check = true
                        lstMain = lst
                        displayRecyclerView(lstMain)
                    }
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }

            displayRecyclerView(lstMain)
        })

        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.style_spinner, spinnerItems)
        binding.spinner.adapter = spinnerAdapter

        binding.btnAddNewPlayList.setOnClickListener {
            showDialogBottom()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun displayRecyclerView(lstData: ArrayList<Playlist>) {
        val layoutRecyclerViewMusic =
            LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
        playlistAdapter = PlaylistAdapter(lstData, 0) {
            findNavController().navigate(R.id.action_playListFragment_to_playlistDetailFragment, it)
        }

        binding.recyclerView.apply {
            layoutManager = layoutRecyclerViewMusic
            adapter = playlistAdapter
        }
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

        val edtTitle: EditText = dialog.findViewById(R.id.edtNamePlaylist)
        val btnYes: Button = dialog.findViewById(R.id.btnYes)
        val btnNo: Button = dialog.findViewById(R.id.btnCancel)
        val spinner: Spinner = dialog.findViewById(R.id.spinnerDialog)

        val spinnerItemsDialog = arrayOf("Public", "Private")
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItemsDialog)
        spinner.adapter = spinnerAdapter

        btnYes.setOnClickListener {
            userViewModel.addPlaylist(
                Playlist(
                    "pl000",
                    edtTitle.text.toString(),
                    R.drawable.ic_playlist_added,
                )
            )

            dialog.dismiss()
        }

        btnNo.setOnClickListener {
            dialog.dismiss()
        }

    }
}