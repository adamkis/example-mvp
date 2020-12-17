package com.example.app.feature.weatherList

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app.BaseApp
import com.example.app.R
import javax.inject.Inject

class WeatherListActivity : AppCompatActivity(), WeatherListContract.View {

    @Inject
    lateinit var presenter: WeatherListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_list)
        BaseApp.component.inject(this)
        setupViewElements()
        presenter.attach(this)
    }

    override fun onDestroy() {
        presenter.unsubscribe()
        super.onDestroy()
    }

    private fun setupViewElements() {
    }

    override fun showError(t: Throwable) {
        Toast.makeText(this, t.toString(), Toast.LENGTH_LONG).show()
    }

    override fun showLoading(isLoading: Boolean) {
    }
}