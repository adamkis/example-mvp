package com.example.app.ui.main

import android.util.Log
import com.example.app.BaseApp
import com.example.app.api.ApiServiceInterface
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
        injectDependency()
    }

    override fun subscribe() {}

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MainContract.View) {
        this.view = view
    }

    override fun loadWeatherData() {
        val subscribe = api.getBudapestWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    sharedPreferencesManager.saveWeatherData(it)
                    view.showWeatherData(it)
                    view.showLoading(false)

//                    val millis = parseDate(it.time)
//                    Log.d("xzxz", "$millis")

                }, {
                    it.printStackTrace()
                    view.showError(it)
                    view.showLoading(false)
                })
        subscriptions.add(subscribe)
    }


    private fun injectDependency() {
        BaseApp.instance.component.inject(this)
    }
}