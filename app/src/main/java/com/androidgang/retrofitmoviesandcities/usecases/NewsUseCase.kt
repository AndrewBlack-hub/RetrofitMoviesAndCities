package com.androidgang.retrofitmoviesandcities.usecases

import com.androidgang.retrofitmoviesandcities.model.NewsResponse
import com.androidgang.retrofitmoviesandcities.service.ApiService
import com.androidgang.retrofitmoviesandcities.service.NetworkService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsUseCase {

    private val newsFields = "id,title,slug,publication_date,body_text,images,place"
    private val textFormat = "plain"

    fun loadNewsWithExtraData(): Observable<NewsResponse> {
        return NetworkService.buildService(ApiService::class.java)
            .getNews(newsFields, textFormat)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}