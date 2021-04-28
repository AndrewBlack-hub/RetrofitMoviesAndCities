package com.androidgang.retrofitmoviesandcities.ui.news

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.androidgang.retrofitmoviesandcities.adapters.NewsAdapter
import com.androidgang.retrofitmoviesandcities.databinding.FragmentNewsBinding
import com.androidgang.retrofitmoviesandcities.model.NewsResponse
import com.androidgang.retrofitmoviesandcities.utilits.TAG

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
        liveDataObservers()
    }

    private fun initialization() {
        initViewModel()
        loadData()
    }

    private fun liveDataObservers() {
        viewModel.getLiveDataOnError.observe(viewLifecycleOwner, Observer {
            Log.e(TAG, "getLiveDataError: ${it.message}")
        })

        viewModel.getLiveDataOnSuccess.observe(viewLifecycleOwner, Observer {
            dataLoaded((it as NewsResponse).results)
        })
    }

    private fun dataLoaded(data: ArrayList<NewsResponse.Result>?) {
        binding.rvNewsList.adapter = NewsAdapter(requireContext())
        (binding.rvNewsList.adapter as NewsAdapter).setList(data)
        newsClickListener()
    }

    private fun newsClickListener() {
        (binding.rvNewsList.adapter as NewsAdapter)
            .onNewsClickListener = object : NewsAdapter.OnNewsClickListener {
            override fun onNewsClick(news: NewsResponse.Result) {
                sendDataToNewsDetails(news)
            }
        }
    }

    private fun sendDataToNewsDetails(news: NewsResponse.Result) {
        val action = NewsFragmentDirections.actionNewsFragmentToNewsDetailsFragment(
            newsTitle = news.title,
            newsDescription = news.bodyText,
            image = news.images?.get(0)?.image
        )
        findNavController().navigate(action)
    }

    private fun loadData() {
        viewModel.loadData()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}