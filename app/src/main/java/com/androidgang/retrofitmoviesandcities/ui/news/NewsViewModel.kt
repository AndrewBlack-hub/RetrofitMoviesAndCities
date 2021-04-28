package com.androidgang.retrofitmoviesandcities.ui.news

import com.androidgang.retrofitmoviesandcities.base.BaseViewModel
import com.androidgang.retrofitmoviesandcities.model.NewsResponse
import com.androidgang.retrofitmoviesandcities.usecases.NewsUseCase

class NewsViewModel : BaseViewModel() {

    private val newsUseCase = NewsUseCase()

    fun loadData() {
        val dis = newsUseCase.loadNewsWithExtraData()
            .subscribe(
                { result -> liveDataOnSuccess.value = result as NewsResponse },
                { throwable -> liveDataOnError.value = throwable }
            )
        cd.add(dis)
    }

}