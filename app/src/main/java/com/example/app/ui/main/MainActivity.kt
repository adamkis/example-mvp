package com.example.app.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.app.BaseApp
import com.example.app.R
import com.example.app.models.WeatherDataResponse
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    private var weatherDataResponse: WeatherDataResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BaseApp.instance.component.inject(this)
        presenter.attach(this)
        presenter.subscribe()
        setupViewElements()
        presenter.showData(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(WEATHER_DATA_INSTANCE_STATE, weatherDataResponse)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        presenter.unsubscribe()
        super.onDestroy()
    }

    private fun setupViewElements() {
        srlRefresh.setOnRefreshListener {
            presenter.loadWeatherData()
        }
    }

    override fun showWeatherData(weatherDataResponse: WeatherDataResponse) {
        this.weatherDataResponse = weatherDataResponse // tODO do not store here
        val weatherSource = weatherDataResponse.consolidatedWeather?.get(0)
        val iconValue = weatherSource?.weatherStateAbbr // TODO move to mapper
        Glide.with(this)
                .load("https://www.metaweather.com/static/img/weather/png/$iconValue.png") // TODO move to mapper
                .into(ivCurrentWeather)
        tvTemp.text = "${weatherSource?.theTemp} Celsius" // TODO use strings resource
        tvDescription.text = weatherSource?.weatherStateName
        tvAgeOfData.text = weatherDataResponse.time
    }

    override fun showError(t: Throwable) {
        Toast.makeText(this, t.toString(), Toast.LENGTH_LONG).show()
    }

    override fun showLoading(isLoading: Boolean) {
        srlRefresh.isRefreshing = isLoading
    }

    companion object {
        const val WEATHER_DATA_INSTANCE_STATE = "WEATHER_DATA_INSTANCE_STATE"
    }
}