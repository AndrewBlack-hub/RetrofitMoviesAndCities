package com.androidgang.retrofitmoviesandcities.ui.newsDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.androidgang.retrofitmoviesandcities.databinding.FragmentNewsDetailsBinding
import com.bumptech.glide.Glide

class NewsDetailsFragment : Fragment() {

    private val args by navArgs<NewsDetailsFragmentArgs>()
    private val newsTitle by lazy { args.newsTitle }
    private val newsDescription by lazy { args.newsDescription }
    private val image by lazy { args.image }

    private var _binding: FragmentNewsDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentNewsDetailsBinding.inflate(layoutInflater, container, false)
        updateUI()
        return binding.root
    }

    private fun updateUI() {
        binding.newsText.text = newsTitle
        binding.newsDescription.text = newsDescription
        Glide.with(this)
            .load(image)
            .into(binding.newsImage)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}