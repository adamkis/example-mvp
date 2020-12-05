package com.example.app.ui.main

import android.os.Bundle
import com.example.app.BaseApp
import com.example.app.api.ApiServiceInterface
import com.example.app.models.WeatherDataResponse
import com.example.app.util.SharedPreferencesManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainPresenter @Inject constructor(
        private val sharedPreferencesManager: SharedPreferencesManager,
        retrofit: Retrofit
) : MainContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: MainContract.View
    private val api: ApiServiceInterface = retrofit.create(ApiServiceInterface::class.java)

    init {
        BaseApp.instance.component.inject(this)
    }

    override fun subscribe() {}

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MainContract.View) {
        this.view = view
    }

    override fun showData(savedInstanceState: Bundle?) {
        (savedInstanceState?.getSerializable(MainActivity.WEATHER_DATA_INSTANCE_STATE)
                as? WeatherDataResponse)?.let {
            if (System.currentTimeMillis() > parseDate(it.time) + DATA_EXPIRATION_TIME_MILLIS) {
                loadWeatherData()
            } else {
                view.showWeatherData(it)
            }
        } ?: run {
            loadWeatherData()
        }
    }

    override fun loadWeatherData() {
        view.showLoading(true)
        val subscribe = api.getBudapestWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    sharedPreferencesManager.saveWeatherData(it)
                    view.showWeatherData(it)
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
        const val DATA_EXPIRATION_TIME_MILLIS = 5 * 1000 // TODO make it 1 minute
    }
}