package com.example.app.feature.weatherList

import android.content.Context
import com.example.app.core.Navigator
import javax.inject.Inject

class WeatherListNavigator @Inject constructor() : Navigator() {

    fun startWeatherList(context: Context) =
            startActivity(context, WeatherListActivity.createIntent(context))
}
