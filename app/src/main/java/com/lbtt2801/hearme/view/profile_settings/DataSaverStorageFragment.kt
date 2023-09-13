package com.lbtt2801.hearme.view.profile_settings

import android.os.Bundle
import android.os.Environment
import android.os.StatFs
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentDataSaverStorageBinding

class DataSaverStorageFragment : Fragment() {
    private var _binding: FragmentDataSaverStorageBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_data_saver_storage, container, false)

        (activity as MainActivity).customToolbar(
            "VISIBLE",
            "Data Saver & Storage",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        )

        (activity as MainActivity).showBottomNav("GONE")

        (activity as MainActivity).binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val stat = StatFs(Environment.getExternalStorageDirectory().getPath())
        val bytesAvailable: Long = stat.blockSizeLong * stat.availableBlocksLong
        val megAvailable = bytesAvailable / (1024 * 1024)
        binding.tvCapacityFree.text = megAvailable.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}