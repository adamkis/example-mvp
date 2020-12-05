package com.example.app.api

import com.example.app.models.WeatherDataResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface WeatherApi {
    @GET("location/804365/")
    fun getBudapestWeather(): Observable<WeatherDataResponse>
}