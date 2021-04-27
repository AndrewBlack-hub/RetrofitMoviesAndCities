package com.androidgang.retrofitmoviesandcities.service

import com.androidgang.retrofitmoviesandcities.model.MoviesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/public-api/v1.4/movies/")
    fun getMovies(@Query("fields") fields: String): Observable<MoviesResponse>

}