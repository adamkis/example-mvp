package com.example.app.core

import android.content.res.Resources
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class ResourceResolver(private val appContext: AppContext) {

    private val resources: Resources
        get() = appContext.context.resources

    fun getString(@StringRes id: Int) = resources.getString(id)

    @ColorInt
    fun getColor(@ColorRes id: Int) = ContextCompat.getColor(appContext.context, id)
}