package com.example.app.feature.weather.model

data class WeatherVM(
        val icon: String,
        val temp: String,
        val description: String,
        val dateTime: String,
        val dateTimeMillis: Long
)