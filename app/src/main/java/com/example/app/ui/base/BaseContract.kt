package com.example.app.ui.base

class BaseContract {

    interface Presenter<in T> {
        fun subscribe()
        fun unsubscribe()
        fun attach(view: T)
    }

    interface View {
        fun showError(t: Throwable)
        fun showLoading(isLoading: Boolean)
    }
}