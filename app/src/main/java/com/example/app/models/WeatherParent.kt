package com.example.app.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WeatherParent(
        @SerializedName("latt_long")
        val lattLong: String = "",
        @SerializedName("woeid")
        val woeid: Int = 0,
        @SerializedName("title")
        val title: String = "",
        @SerializedName("location_type")
        val locationType: String = ""
): Serializable
