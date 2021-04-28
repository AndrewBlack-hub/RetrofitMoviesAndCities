package com.androidgang.retrofitmoviesandcities.usecases

import com.androidgang.retrofitmoviesandcities.model.MoviesResponse
import com.androidgang.retrofitmoviesandcities.service.ApiService
import com.androidgang.retrofitmoviesandcities.service.NetworkService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviesUseCase {

    private val moviesFields: String = "images,stars,id,title,poster,body_text,locale,running_time,age_restriction,trailer,country"

    fun loadMoviesWithExtraData(): Observable<MoviesResponse> {
        return NetworkService.buildService(ApiService::class.java)
            .getMovies(moviesFields)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}