package com.example.app.domain

import android.content.Context
import com.example.app.R
import com.example.app.models.WeatherDataResponse
import com.example.app.ui.model.WeatherVM
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherDataMapper @Inject constructor(private val context: Context) {

    fun map(weatherDataResponse: WeatherDataResponse): WeatherVM {
        val weatherSource = weatherDataResponse.consolidatedWeather?.get(0)
        return WeatherVM(
                icon = "https://www.metaweather.com/static/img/weather/png/" +
                        "${weatherSource?.weatherStateAbbr}.png",
                temp = context.getString(R.string.temp_in_celsius, weatherSource?.theTemp),
                description = weatherSource?.weatherStateName ?: "",
                dateTime = weatherDataResponse.time
        )
    }
}