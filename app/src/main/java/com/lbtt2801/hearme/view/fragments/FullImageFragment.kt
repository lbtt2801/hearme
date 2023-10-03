package com.lbtt2801.hearme.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.lbtt2801.hearme.MainActivity
import com.lbtt2801.hearme.R
import com.lbtt2801.hearme.databinding.FragmentFullImageBinding
import com.squareup.picasso.Picasso

class FullImageFragment : Fragment() {
    private lateinit var binding: FragmentFullImageBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var url: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        url = arguments?.getString("url").toString()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_full_image, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = activity as MainActivity

        val options = RequestOptions()
            .error(R.drawable.ellipse)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .priority(Priority.HIGH)
            .dontTransform()
            .format(DecodeFormat.PREFER_ARGB_8888)

        Glide.with(this)
            .load(url)
            .apply(options)
            .into(binding.imageView)

        binding.imageButtonClose.setOnClickListener() {
            findNavController().popBackStack()
        }
    }

    override fun onResume() {
        super.onResume()
        mainActivity.customToolbar(
            "GONE"
        )
        mainActivity.showBottomNav("GONE")
    }
}