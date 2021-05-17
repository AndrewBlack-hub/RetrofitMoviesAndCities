package com.androidgang.retrofitmoviesandcities.service

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {

    private const val BASE_URL = "https://kudago.com/public-api/"

    private val logger = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(logger)

    private val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttp.apply {
            Interceptor { chain ->
                val builder = chain.request().newBuilder()
                return@Interceptor chain.proceed(builder.build())
            }
        }.build())

    private val retrofit = builder.build()//create retrofit instance

    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}