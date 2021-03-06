package com.example.app.feature.weather

import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.app.R
import com.example.app.core.BaseActivity
import com.example.app.feature.weather.model.WeatherVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_weather.*
import javax.inject.Inject

@AndroidEntryPoint
class WeatherActivity : BaseActivity(), WeatherContract.View {

    @Inject
    lateinit var presenter: WeatherPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        setupViewElements()
        presenter.attach(this)
        presenter.showData()
    }

    override fun onDestroy() {
        presenter.unsubscribe()
        super.onDestroy()
    }

    private fun setupViewElements() {
        srlRefresh.setOnRefreshListener {
            presenter.loadWeatherData()
        }
        fab.setOnClickListener {
            presenter.onFabClicked()
        }
    }

    override fun showWeatherData(weatherVM: WeatherVM) {
        Glide.with(this).load(weatherVM.icon).into(ivCurrentWeather)
        tvTemp.text = weatherVM.temp
        tvDescription.text = weatherVM.description
        tvDateTime.text = weatherVM.dateTime
    }

    override fun showError(t: Throwable) {
        Toast.makeText(this, t.toString(), Toast.LENGTH_LONG).show()
    }

    override fun showLoading(isLoading: Boolean) {
        srlRefresh.isRefreshing = isLoading
    }
}