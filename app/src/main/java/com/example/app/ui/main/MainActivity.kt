package com.example.app.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.app.BaseApp
import com.example.app.R
import com.example.app.ui.model.WeatherVM
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BaseApp.component.inject(this)
        presenter.attach(this)
        presenter.subscribe()
        setupViewElements()
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