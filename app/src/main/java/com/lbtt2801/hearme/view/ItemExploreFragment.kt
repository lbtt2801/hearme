package com.lbtt2801.hearme.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentItemExploreBinding

class ItemExploreFragment : Fragment() {
    private var _binding: FragmentItemExploreBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_item_explore, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getString("name")
        (activity as MainActivity).customToolbar(
            "VISIBLE", title, R.color.transparent, R.drawable.ic_arrow_back,
            showIcMore = false,
            showIcFilter = false,
            showIcSearch = true
        )

        binding.tvWeeklyAlbum.text = "Weekly Album $title"
        binding.tvWeeklySong.text = "Weekly Song $title"
        binding.tvDailyViral.text = "Daily Viral $title"

        binding.cardViewTopAlbumGlobal.setOnClickListener {
            val detail = "The most played albums from last week."
//            sendData(
//                binding.tvInCardTopAlbumGlobal.text.toString(),
//                0,
//                binding.tvTopAlbumGlobal.text.toString(),
//                detail
//            )
            val bundle = Bundle().apply {
//                putInt("background", background)
//                putString("tvInCard", tvInCard)
//                putString("tvTitle", tvTitle)
                putString("tvDetail", detail)
            }
            ItemExploreFragment().arguments = bundle
            findNavController().navigate(R.id.topItemFragment)
        }

    }

    private fun sendData(tvInCard: String, background: Int, tvTitle: String, tvDetail: String) {
        val bundle = Bundle().apply {
            putInt("background", background)
            putString("tvInCard", tvInCard)
            putString("tvTitle", tvTitle)
            putString("tvDetail", tvDetail)
        }
        this.arguments = bundle
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}