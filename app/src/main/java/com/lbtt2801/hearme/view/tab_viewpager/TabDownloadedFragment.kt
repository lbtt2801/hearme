package com.lbtt2801.hearme.view.tab_viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.MusicAdapter
import com.lbtt2801.hearme.databinding.FragmentTabDownloadedBinding
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.viewmodel.UserViewModel
class TabDownloadedFragment : Fragment() {
    private lateinit var binding: FragmentTabDownloadedBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var musicAdapter: MusicAdapter
    private var lst: ArrayList<Music>? = null
    private var email: String? = ""
    private var spinnerItems = arrayOf("Recently Added", "Added Before")

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab_downloaded, container, false)

        mainActivity = (activity as MainActivity)
        lst = ArrayList()
        email = mainActivity.email

        mainActivity.customToolbar(
            "VISIBLE",
            "Downloads",
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

        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.style_spinner, spinnerItems)
        binding.spinner.adapter = spinnerAdapter

        userViewModel.lstDataUser.observe((activity as MainActivity), Observer { arrayList ->
            lst = arrayList.first { user -> user.email == email }.listMusicsDownloaded
            val lstP0 = lst as ArrayList<Music>
            val lstP1 = lst as ArrayList<Music>
            if (!lst.isNullOrEmpty()) {
                val lstData: List<Music> = lst!!.filter { it.category.categoryID == "ca002" }

                binding.spinner.setSelection(0)
                binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        if (p2 == 0)
                            displayRecyclerView(lstP0)
                        else
                            displayRecyclerView(lstP1)
                    }
                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }
                }
            }
        })
    }

    private fun displayRecyclerView(lstData: ArrayList<Music>) {
        val layoutRecyclerViewMusic =
            LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
        musicAdapter = MusicAdapter(lstData, 2,this)
        binding.recyclerView.apply {
            layoutManager = layoutRecyclerViewMusic
            adapter = musicAdapter
        }
    }
}