package com.example.app.data.api

import com.example.app.data.model.WeatherDataResponse
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET

interface WeatherApi {
    @GET("location/804365/")
    fun getBudapestWeather(): Observable<WeatherDataResponse>

    @GET("https://oltopont.dpckorhaz.hu/oltokozpont")
//    @GET("https://www.reddit.com/new/")
    fun getWebsite(): Observable<ResponseBody>
}