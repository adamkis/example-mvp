package com.example.app.data.model

import com.google.gson.annotations.SerializedName

data class WeatherParent(
        @SerializedName("latt_long")
        val lattLong: String = "",
        @SerializedName("woeid")
        val woeid: Int = 0,
        @SerializedName("title")
        val title: String = "",
        @SerializedName("location_type")
        val locationType: String = ""
)
