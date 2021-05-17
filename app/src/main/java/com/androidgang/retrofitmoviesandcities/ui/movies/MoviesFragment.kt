package com.androidgang.retrofitmoviesandcities.ui.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.androidgang.retrofitmoviesandcities.R
import com.androidgang.retrofitmoviesandcities.adapters.MoviesAdapter
import com.androidgang.retrofitmoviesandcities.adapters.NewsAdapter
import com.androidgang.retrofitmoviesandcities.databinding.FragmentMoviesBinding
import com.androidgang.retrofitmoviesandcities.model.MoviesResponse
import com.androidgang.retrofitmoviesandcities.model.NewsResponse
import com.androidgang.retrofitmoviesandcities.utilits.TAG

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
        liveDataObservers()
    }

    private fun liveDataObservers() {
        viewModel.getLiveDataOnError.observe(viewLifecycleOwner, Observer {
            Log.e(TAG, "getLiveDataOnErrors: ${it.message}")
        })

        viewModel.getLiveDataOnSuccess.observe(viewLifecycleOwner, Observer {
            dataLoaded((it as MoviesResponse).results)
        })
    }

    private fun dataLoaded(data: ArrayList<MoviesResponse.Result>?) {
        binding.rvMoviesList.adapter = MoviesAdapter(requireContext())
        (binding.rvMoviesList.adapter as MoviesAdapter).setList(data)
        movieClickListener()
    }

    private fun movieClickListener() {
        (binding.rvMoviesList.adapter as MoviesAdapter)
            .onMovieClickListener = object : MoviesAdapter.OnMovieClickListener {
            override fun onMovieClick(movie: MoviesResponse.Result) {
                sendDataToMovieDetails(movie)
            }
        }
    }

    private fun sendDataToMovieDetails(movie: MoviesResponse.Result) {
        val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(
            titleMovie = movie.title,
            descriptionMovie = movie.bodyText,
            country = movie.country,
            ageRestriction = movie.ageRestriction,
            stars = movie.stars,
            trailerUrl = movie.trailer,
            image = movie.images?.get(0)?.image
        )
        findNavController().navigate(action)
    }

    private fun initialization() {
        initViewModel()
        loadData()
    }

    private fun loadData() {
        viewModel.loadData()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        viewModel.disposeObservers()
    }
}