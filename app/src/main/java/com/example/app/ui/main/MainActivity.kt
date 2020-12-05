package com.example.app.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.app.BaseApp
import com.example.app.R
import com.example.app.models.WeatherDataResponse
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity: AppCompatActivity(), MainContract.View {

    @Inject lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()
        presenter.attach(this)
        presenter.subscribe()
        presenter.loadWeatherData()
    }

    override fun onDestroy() {
        presenter.unsubscribe()
        super.onDestroy()
    }

    override fun showWeatherData(weatherDataResponse: WeatherDataResponse) {
        val iconValue = weatherDataResponse.consolidatedWeather?.get(0)?.weatherStateAbbr
        Glide.with(this)
                .load("https://www.metaweather.com/static/img/weather/png/$iconValue.png")
                .into(ivCurrentWeather)
    }

    override fun showError(t: Throwable) {
        Toast.makeText(this, t.toString(), Toast.LENGTH_LONG).show()
    }

    private fun injectDependency() {
        BaseApp.instance.component.inject(this)
    }
}