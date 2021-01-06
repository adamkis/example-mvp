package com.example.app.data.api

import com.example.app.data.model.WeatherDataResponse
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET

interface WeatherApi {
    @GET("oltokozpont")
    fun getBudapestWeather(): Observable<ResponseBody>
}