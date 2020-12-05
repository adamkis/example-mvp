package com.example.app.ui.main

import com.example.app.ui.base.BaseContract
import com.example.app.ui.model.WeatherVM

class MainContract {

    interface View : BaseContract.View {
        fun showWeatherData(weatherVM: WeatherVM)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadWeatherData()
        fun showData()
    }
}