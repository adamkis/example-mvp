package com.example.app.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.app.BaseApp
import com.example.app.R
import javax.inject.Inject

class MainActivity: AppCompatActivity(), MainContract.View {

    @Inject lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()
        presenter.attach(this)
        presenter.subscribe()
        presenter.loadData()
    }

    override fun onDestroy() {
        presenter.unsubscribe()
        super.onDestroy()
    }

    override fun showToast(input: String) {
        Toast.makeText(this, input, Toast.LENGTH_LONG).show()
    }

    private fun injectDependency() {
        BaseApp.instance.component.inject(this)
    }
}