package com.example.app.core

class BaseContract {

    interface Presenter<in T> {
        fun unsubscribe()
        fun attach(view: T)
    }

    interface View {
        fun showError(t: Throwable)
        fun showLoading(isLoading: Boolean)
    }
}