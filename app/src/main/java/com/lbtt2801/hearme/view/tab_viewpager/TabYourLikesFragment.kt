package com.lbtt2801.hearme.view.tab_viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.data.adapter.MusicAdapter
import com.lbtt2801.hearme.databinding.FragmentTabYouLikeBinding
import com.lbtt2801.hearme.model.Music
import com.lbtt2801.hearme.viewmodel.UserViewModel

class TabYourLikesFragment : Fragment() {
    private lateinit var binding: FragmentTabYouLikeBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var musicAdapter: MusicAdapter
    private var lst: ArrayList<Music> = ArrayList()
    private var email: String? = ""
    private var spinnerItems = arrayOf("Recently Added", "Added Before")

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_tab_you_like, container, false)

        mainActivity = (activity as MainActivity)
        email = mainActivity.email

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.style_spinner, spinnerItems)
        binding.spinner.adapter = spinnerAdapter

        userViewModel.lstDataUser.observe((activity as MainActivity), Observer { arrayList ->
            lst = arrayList.first { user -> user.email == email }.listMusicsLoved

            if (lst.isNotEmpty()) {
                val lstData = lst.filter { it.category.categoryID == "ca002" } as ArrayList<Music>
                val lstP1 = lst.filter { it.category.categoryID == "ca002" } as ArrayList<Music>
                val lstP0 = ArrayList<Music>()

                // dao nguoc mang lstData gan cho lstP0
                for (i in lstData.indices) {
                    lstP0.add(i, lstData.removeLast())
                }

                binding.spinner.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            p0: AdapterView<*>?,
                            p1: View?,
                            p2: Int,
                            p3: Long,
                        ) {
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
        musicAdapter = MusicAdapter(lstData, 2, this)
        binding.recyclerView.apply {
            layoutManager = layoutRecyclerViewMusic
            adapter = musicAdapter
        }
    }
}