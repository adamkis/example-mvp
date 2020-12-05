package com.example.app.ui.main

import com.example.app.ui.base.BaseContract

class MainContract {

    interface View: BaseContract.View {
        fun showToast(input: String)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData()
    }
}