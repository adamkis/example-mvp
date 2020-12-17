package com.example.app.core

import android.content.Context

/**
 * Wrapper class to ensure that the injected application context is handled as application context
 */
class AppContext(val context: Context)