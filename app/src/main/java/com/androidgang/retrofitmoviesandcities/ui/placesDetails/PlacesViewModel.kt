package com.androidgang.retrofitmoviesandcities.ui.placesDetails

import com.androidgang.retrofitmoviesandcities.base.BaseViewModel
import com.androidgang.retrofitmoviesandcities.model.PlacesResponse
import com.androidgang.retrofitmoviesandcities.usecases.PlacesUseCase

class PlacesViewModel: BaseViewModel() {

    private val placesUseCase = PlacesUseCase()

    fun loadData() {
        val dis = placesUseCase.loadPlacesWithExtraData()
            .subscribe(
                { result -> liveDataOnSuccess.value = result as PlacesResponse},
                { throwable -> liveDataOnError.value = throwable}
            )
        cd.add(dis)
    }
}