package com.example.app.feature.weather

import com.example.app.BaseApp
import com.example.app.data.SharedPreferencesManager
import com.example.app.data.api.WeatherApi
import com.example.app.feature.weather.mapper.WeatherDataMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherPresenter @Inject constructor(
        private val sharedPreferencesManager: SharedPreferencesManager,
        private val weatherApi: WeatherApi,
        private val weatherDataMapper: WeatherDataMapper
) : WeatherContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: WeatherContract.View

    init {
        BaseApp.component.inject(this)
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: WeatherContract.View) {
        this.view = view
    }

    override fun showData() {
        (sharedPreferencesManager.loadWeatherData())?.let {
            val weatherVM = weatherDataMapper.map(it)
            if (System.currentTimeMillis() > weatherVM.dateTimeMillis + DATA_EXPIRATION_TIME_MILLIS) {
                loadWeatherData()
            } else {
                view.showWeatherData(weatherVM)
            }
        } ?: run {
            loadWeatherData()
        }
    }

    override fun loadWeatherData() {
        view.showLoading(true)
        val subscribe = weatherApi.getBudapestWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    sharedPreferencesManager.saveWeatherData(it)
                    view.showWeatherData(weatherDataMapper.map(it))
                    view.showLoading(false)
                }, {
                    it.printStackTrace()
                    view.showError(it)
                    (sharedPreferencesManager.loadWeatherData())?.let { savedData ->
                        view.showWeatherData(weatherDataMapper.map(savedData))
                    }
                    view.showLoading(false)
                })
        subscriptions.add(subscribe)
    }

    companion object {
        const val DATA_EXPIRATION_TIME_MILLIS = 60 * 1000 // 1 minute
    }
}