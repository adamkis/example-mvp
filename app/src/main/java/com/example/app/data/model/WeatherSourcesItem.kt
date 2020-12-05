package com.example.app.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WeatherSourcesItem(
        @SerializedName("crawl_rate")
        val crawlRate: Int = 0,
        @SerializedName("title")
        val title: String = "",
        @SerializedName("slug")
        val slug: String = "",
        @SerializedName("url")
        val url: String = ""
): Serializable
