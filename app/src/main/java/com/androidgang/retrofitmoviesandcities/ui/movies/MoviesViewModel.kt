package com.androidgang.retrofitmoviesandcities.ui.movies

import com.androidgang.retrofitmoviesandcities.base.BaseViewModel
import com.androidgang.retrofitmoviesandcities.model.MoviesResponse
import com.androidgang.retrofitmoviesandcities.usecases.MoviesUseCase

class MoviesViewModel : BaseViewModel() {

    private val moviesUseCase = MoviesUseCase()

    fun loadData() {
        val dis = moviesUseCase.loadMoviesWithExtraData()
            .subscribe(
                { result -> liveDataOnSuccess.value = result as MoviesResponse },
                { throwable -> liveDataOnError.value = throwable }
            )
        cd.add(dis)
    }

}