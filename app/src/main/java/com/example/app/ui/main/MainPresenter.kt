package com.example.app.ui.main

import com.example.app.BaseApp
import com.example.app.api.ApiServiceInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class MainPresenter @Inject constructor(
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
                    view.showWeatherData(it)
                }, {
                    it.printStackTrace()
                    view.showError(it)
                })
        subscriptions.add(subscribe)
    }


    private fun injectDependency() {
        BaseApp.instance.component.inject(this)
    }
}