package com.example.app.data.api

import com.example.app.data.model.WeatherDataResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface WeatherApi {
    @GET("location/804365/")
    fun getBudapestWeather(): Observable<WeatherDataResponse>
}