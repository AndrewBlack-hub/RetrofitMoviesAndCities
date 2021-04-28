package com.androidgang.retrofitmoviesandcities.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidgang.retrofitmoviesandcities.base.BaseViewModel
import com.androidgang.retrofitmoviesandcities.model.MoviesResponse
import com.androidgang.retrofitmoviesandcities.usecases.MoviesUseCase

class MoviesViewModel : BaseViewModel() {

    private val moviesUseCase = MoviesUseCase()

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun loadData() {
        val dis = moviesUseCase.loadMoviesWithExtraData()
            .subscribe(
                { result -> liveDataOnSuccess.value = result as MoviesResponse },
                { throwable -> liveDataOnError.value = throwable }
            )
        cd.add(dis)
    }

}