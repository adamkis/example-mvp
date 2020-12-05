package com.example.app.ui.main

import android.util.Log
import com.example.app.api.ApiServiceInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter: MainContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: MainContract.View
    private val api: ApiServiceInterface = ApiServiceInterface.create()

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MainContract.View) {
        this.view = view
    }

    override fun loadData() {
        val subscribe = api.getBudapestWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showToast(it.toString())
                    Log.d("Weather", "Printing Budapest weather")
                    Log.d("Weather", it.toString())
                }, { error ->
                    error.printStackTrace()
                    Log.d("Weather", error.message!!)
                    view.showToast(error.message!!)
                })
        subscriptions.add(subscribe)
    }

}