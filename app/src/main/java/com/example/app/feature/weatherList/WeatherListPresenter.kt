package com.example.app.feature.weatherList

import android.util.Log
import com.example.app.R
import com.example.app.core.ResourceResolver
import com.example.app.data.api.WeatherApi
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class WeatherListPresenter @Inject constructor(
        private val resourceResolver: ResourceResolver
) : WeatherListContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private lateinit var view: WeatherListContract.View

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: WeatherListContract.View) {
        this.view = view
    }

    override fun onItemClicked(action: WeatherListAdapter.Action) {
        when (action) {
            is WeatherListAdapter.Action.ItemClicked -> {
                Log.d("WeatherListPresenter", action.text)
                Log.d("WeatherListPresenter", resourceResolver.getString(R.string.app_name))
            }
        }
    }
}