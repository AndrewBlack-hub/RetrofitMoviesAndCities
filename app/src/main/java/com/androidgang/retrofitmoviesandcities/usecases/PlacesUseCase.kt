package com.androidgang.retrofitmoviesandcities.usecases

import com.androidgang.retrofitmoviesandcities.model.PlacesResponse
import com.androidgang.retrofitmoviesandcities.service.ApiService
import com.androidgang.retrofitmoviesandcities.service.NetworkService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PlacesUseCase {

    private val placesFields = "id,title,coords,short_title,phone,body_text,images,address"

    fun loadPlacesWithExtraData(): Observable<PlacesResponse> {
        return NetworkService.buildService(ApiService::class.java)
            .getPlaces(placesFields)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}