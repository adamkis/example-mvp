package com.example.app.feature.weatherList

import com.example.app.core.BaseContract
import com.example.app.feature.weather.model.WeatherVM

class WeatherListContract {

    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter<View> {
        fun onItemClicked(action: WeatherListAdapter.Action)
    }
}