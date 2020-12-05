package com.example.app.feature.weather

import com.example.app.core.BaseContract
import com.example.app.feature.weather.model.WeatherVM

class MainContract {

    interface View : BaseContract.View {
        fun showWeatherData(weatherVM: WeatherVM)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadWeatherData()
        fun showData()
    }
}