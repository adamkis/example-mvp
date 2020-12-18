package com.example.app.core

import android.content.Context
import android.content.res.Resources
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceResolver @Inject constructor(
        @ApplicationContext private val appContext: Context
) {

    private val resources: Resources
        get() = appContext.resources

    fun getString(@StringRes id: Int) = resources.getString(id)

    @ColorInt
    fun getColor(@ColorRes id: Int) = ContextCompat.getColor(appContext, id)
}
