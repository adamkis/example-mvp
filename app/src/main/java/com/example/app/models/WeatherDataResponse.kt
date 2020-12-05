package com.example.app.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WeatherDataResponse(
        @SerializedName("sun_set")
        val sunSet: String = "",
        @SerializedName("parent")
        val weatherParent: WeatherParent,
        @SerializedName("sources")
        val sources: List<WeatherSourcesItem>?,
        @SerializedName("latt_long")
        val lattLong: String = "",
        @SerializedName("timezone")
        val timezone: String = "",
        @SerializedName("timezone_name")
        val timezoneName: String = "",
        @SerializedName("woeid")
        val woeid: Int = 0,
        @SerializedName("sun_rise")
        val sunRise: String = "",
        @SerializedName("consolidated_weather")
        val consolidatedWeather: List<ConsolidatedWeatherItem>?,
        @SerializedName("time")
        val time: String = "",
        @SerializedName("title")
        val title: String = "",
        @SerializedName("location_type")
        val locationType: String = ""
): Serializable
