package com.androidgang.retrofitmoviesandcities.ui.movieDetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.androidgang.retrofitmoviesandcities.R
import com.androidgang.retrofitmoviesandcities.databinding.FragmentMovieDetailsBinding
import com.bumptech.glide.Glide

class MovieDetailsFragment : Fragment() {

    private val args by navArgs<MovieDetailsFragmentArgs>()
    private val titleMovie by lazy { args.titleMovie }
    private val descriptionMovie by lazy { args.descriptionMovie }
    private val country by lazy { args.country }
    private val ageRestriction by lazy { args.ageRestriction }
    private val stars by lazy { args.stars }
    private val trailerUrl by lazy { args.trailerUrl }
    private val image by lazy { args.image }


    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentMovieDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()
        watchTrailer()
    }

    private fun watchTrailer() {
        binding.trailerContainer.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl)))
        }
    }

    private fun updateUI() {
        binding.movieText.text = titleMovie
        binding.movieCountry.text = country
        binding.movieDescription.text = descriptionMovie
        binding.movieAge.text = ageRestriction
        Glide.with(this)
            .load(image)
            .into(binding.movieImage)
        binding.movieStars.text = stars
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}