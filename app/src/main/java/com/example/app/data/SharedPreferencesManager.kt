package com.example.app.data

import android.content.Context
import com.example.app.data.model.WeatherDataResponse
import com.google.gson.Gson
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(
        context: Context,
        private val gson: Gson
) {

    private val sharedPref = context.getSharedPreferences(KEY_SHARED_PREF_FILE, Context.MODE_PRIVATE)

    fun saveWeatherData(weatherDataResponse: WeatherDataResponse) {
        with (sharedPref.edit()) {
            putString(KEY_WEATHER_DATA, gson.toJson(weatherDataResponse))
            apply()
        }
    }

    fun loadWeatherData(): WeatherDataResponse? {
        return gson.fromJson(sharedPref.getString(KEY_WEATHER_DATA, null),
                WeatherDataResponse::class.java)
    }

    companion object {
        const val KEY_SHARED_PREF_FILE = "KEY_SHARED_PREF_FILE_EXAMPLE_APP"
        const val KEY_WEATHER_DATA = "KEY_WEATHER_DATA"
    }
}