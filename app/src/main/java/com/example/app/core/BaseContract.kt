package com.example.app.core

import android.content.Context

class BaseContract {

    interface Presenter<in T> {
        fun unsubscribe()
        fun attach(view: T)
    }

    interface View {
        fun viewContext(): Context
        fun showError(t: Throwable)
        fun showLoading(isLoading: Boolean)
    }
}