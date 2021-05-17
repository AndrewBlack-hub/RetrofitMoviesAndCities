package com.androidgang.retrofitmoviesandcities.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class PlacesResponse {

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
    var results: ArrayList<Result>? = null

    inner class Image {
        @SerializedName("image")
        @Expose
        var image: String? = null

        @SerializedName("source")
        @Expose
        var source: Source? = null
    }

    inner class Coords {
        @SerializedName("lat")
        @Expose
        var lat: Float? = null

        @SerializedName("lon")
        @Expose
        var lon: Float? = null
    }

    inner class Result {
        @SerializedName("id")
        @Expose
        var id: Int? = null

        @SerializedName("title")
        @Expose
        var title: String? = null

        @SerializedName("address")
        @Expose
        var address: String? = null

        @SerializedName("phone")
        @Expose
        var phone: String? = null

        @SerializedName("body_text")
        @Expose
        var bodyText: String? = null

        @SerializedName("coords")
        @Expose
        var coords: Coords? = null

        @SerializedName("images")
        @Expose
        var images: List<Image>? = null

        @SerializedName("short_title")
        @Expose
        var shortTitle: String? = null
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