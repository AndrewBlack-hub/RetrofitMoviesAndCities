package com.androidgang.retrofitmoviesandcities.ui.placesDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.androidgang.retrofitmoviesandcities.databinding.FragmentPlaceDetailsBinding
import com.bumptech.glide.Glide

class PlaceDetailsFragment : Fragment() {

    private val args by navArgs<PlaceDetailsFragmentArgs>()
    private val title by lazy { args.title }
    private val description by lazy { args.description }
    private val address by lazy { args.address }
    private val image by lazy { args.image }

    private var _binding: FragmentPlaceDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlaceDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()
    }

    private fun updateUI() {
        binding.placeText.text = title
        binding.placeDescription.text = description
        binding.placeAddress.text = address
//        Glide.with(this)
//            .load(image)
//            .into(binding.placeImage)
        Glide.with(this).load(image).placeholder(android.R.drawable.progress_indeterminate_horizontal)
            .error(android.R.drawable.stat_notify_error).into(binding.placeImage);
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}