package com.example.app.core

import android.content.Context
import android.content.Intent
import javax.inject.Inject

open class Navigator @Inject constructor() {

    protected fun startActivity(context: Context, intent: Intent) = context.startActivity(intent.apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    })
}