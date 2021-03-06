package com.example.app.feature.weatherList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.R
import com.example.app.core.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_weather_list.*
import javax.inject.Inject

@AndroidEntryPoint
class WeatherListActivity : BaseActivity(), WeatherListContract.View {

    @Inject
    lateinit var presenter: WeatherListPresenter

    private val weatherListAdapter by lazy { WeatherListAdapter(presenter::onItemClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_list)
        setupViewElements()
        presenter.attach(this)
        showList()
    }

    private fun setupViewElements() {
        rvList.apply {
            adapter = weatherListAdapter
            layoutManager = LinearLayoutManager(this@WeatherListActivity)
        }
    }

    private fun showList() {
        weatherListAdapter.setItems(arrayListOf(
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"
        ))
    }

    override fun onDestroy() {
        presenter.unsubscribe()
        super.onDestroy()
    }

    override fun showError(t: Throwable) {
        Toast.makeText(this, t.toString(), Toast.LENGTH_LONG).show()
    }

    override fun showLoading(isLoading: Boolean) {
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, WeatherListActivity::class.java)
    }
}
