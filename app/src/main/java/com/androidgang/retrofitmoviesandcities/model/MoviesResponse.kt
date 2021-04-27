package com.androidgang.retrofitmoviesandcities.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class MoviesResponse {
    @SerializedName("count")
    @Expose
    var count: Int? = null

    @SerializedName("next")
    @Expose
    var next: String? = null

    @SerializedName("previous")
    @Expose
    var previous: Any? = null

    @SerializedName("results")
    @Expose
    var results: List<Result>? = null

    inner class Image {
        @SerializedName("image")
        @Expose
        var image: String? = null

        @SerializedName("source")
        @Expose
        var source: Source? = null
    }

    inner class Poster {
        @SerializedName("image")
        @Expose
        var image: String? = null

        @SerializedName("source")
        @Expose
        var source: Source? = null
    }

    inner class Result {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("title")
        @Expose
        var title: String? = null

        @SerializedName("body_text")
        @Expose
        var bodyText: String? = null

        @SerializedName("locale")
        @Expose
        var locale: String? = null

        @SerializedName("country")
        @Expose
        var country: String? = null

        @SerializedName("running_time")
        @Expose
        var runningTime: Int? = null

        @SerializedName("age_restriction")
        @Expose
        var ageRestriction: String? = null

        @SerializedName("stars")
        @Expose
        var stars: String? = null

        @SerializedName("trailer")
        @Expose
        var trailer: String? = null

        @SerializedName("images")
        @Expose
        var images: List<Image>? = null

        @SerializedName("poster")
        @Expose
        var poster: Poster? = null
    }

    inner class Source {
        @SerializedName("name")
        @Expose
        var name: String? = null

        @SerializedName("link")
        @Expose
        var link: String? = null
    }
}