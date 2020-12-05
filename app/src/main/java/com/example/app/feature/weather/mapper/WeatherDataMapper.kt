package com.example.app.feature.weather.mapper

import android.content.Context
import com.example.app.R
import com.example.app.data.model.WeatherDataResponse
import com.example.app.feature.weather.model.WeatherVM
import java.text.SimpleDateFormat
import java.util.*
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
                dateTime = weatherDataResponse.time,
                dateTimeMillis = parseDate(weatherDataResponse.time)
        )
    }

    private fun parseDate(dateString: String): Long {
        val date: Date? = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX", Locale.US)
                .parse(dateString)
        return date?.time ?: 0
    }
}