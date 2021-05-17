package com.androidgang.retrofitmoviesandcities.service

import com.androidgang.retrofitmoviesandcities.model.MoviesResponse
import com.androidgang.retrofitmoviesandcities.model.NewsResponse
import com.androidgang.retrofitmoviesandcities.model.PlacesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/public-api/v1.4/movies/")
    fun getMovies(
        @Query("fields") fields: String,
        @Query("text_format") textFormat: String
    ): Observable<MoviesResponse>

    @GET("/public-api/v1.4/news/")
    fun getNews(
        @Query("fields") fields: String,
        @Query("text_format") textFormat: String
    ): Observable<NewsResponse>

    @GET("/public-api/v1.4/places/")
    fun getPlaces(
        @Query("fields") fields: String
    ): Observable<PlacesResponse>

}