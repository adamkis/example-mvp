package com.example.app.ui.main

import com.example.app.models.WeatherDataResponse
import com.example.app.ui.base.BaseContract

class MainContract {

    interface View: BaseContract.View {
        fun showWeatherData(weatherDataResponse: WeatherDataResponse)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadWeatherData()
    }
}