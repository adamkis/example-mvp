package com.example.app.ui.main

import com.example.app.BaseApp
import com.example.app.api.WeatherApi
import com.example.app.domain.WeatherDataMapper
import com.example.app.util.SharedPreferencesManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainPresenter @Inject constructor(
        private val sharedPreferencesManager: SharedPreferencesManager,
        private val weatherApi: WeatherApi,
        private val weatherDataMapper: WeatherDataMapper
) : MainContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: MainContract.View

    init {
        BaseApp.component.inject(this)
    }

    override fun subscribe() {}

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MainContract.View) {
        this.view = view
    }

    override fun showData() {
        (sharedPreferencesManager.loadWeatherData())?.let {
            if (System.currentTimeMillis() > parseDate(it.time) + DATA_EXPIRATION_TIME_MILLIS) {
                loadWeatherData()
            } else {
                view.showWeatherData(weatherDataMapper.map(it))
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
                    view.showLoading(false)
                })
        subscriptions.add(subscribe)
    }

    private fun parseDate(dateString: String): Long {
        // TODO move this to mapper
        val date: Date? = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX", Locale.US)
                .parse(dateString)
        return date?.time ?: 0
    }

    companion object {
        const val DATA_EXPIRATION_TIME_MILLIS = 60 * 1000
    }
}