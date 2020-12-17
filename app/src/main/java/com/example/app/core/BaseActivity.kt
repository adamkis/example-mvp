package com.example.app.core

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), BaseContract.View {
    override fun viewContext(): Context = this
}