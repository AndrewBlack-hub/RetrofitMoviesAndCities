package com.androidgang.retrofitmoviesandcities.ui.places

import com.androidgang.retrofitmoviesandcities.base.BaseViewModel
import com.androidgang.retrofitmoviesandcities.model.PlacesResponse
import com.androidgang.retrofitmoviesandcities.usecases.PlacesUseCase
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class PlacesViewModel : BaseViewModel() {

    private val placesUseCase = PlacesUseCase()

    fun loadData() {
        val dis = placesUseCase.loadPlacesWithExtraData()
            .subscribe(
                { result -> liveDataOnSuccess.value = result as PlacesResponse },
                { throwable -> liveDataOnError.value = throwable }
            )
        cd.add(dis)
    }
}