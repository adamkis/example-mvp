package com.example.app.models

import com.google.gson.annotations.SerializedName

data class Parent(@SerializedName("latt_long")
                  val lattLong: String = "",
                  @SerializedName("woeid")
                  val woeid: Int = 0,
                  @SerializedName("title")
                  val title: String = "",
                  @SerializedName("location_type")
                  val locationType: String = "")


data class WeatherDataResponse(@SerializedName("sun_set")
                               val sunSet: String = "",
                               @SerializedName("parent")
                               val parent: Parent,
                               @SerializedName("sources")
                               val sources: List<SourcesItem>?,
                               @SerializedName("latt_long")
                               val lattLong: String = "",
                               @SerializedName("timezone")
                               val timezone: String = "",
                               @SerializedName("timezone_name")
                               val timezoneName: String = "",
                               @SerializedName("woeid")
                               val woeid: Int = 0,
                               @SerializedName("sun_rise")
                               val sunRise: String = "",
                               @SerializedName("consolidated_weather")
                               val consolidatedWeather: List<ConsolidatedWeatherItem>?,
                               @SerializedName("time")
                               val time: String = "",
                               @SerializedName("title")
                               val title: String = "",
                               @SerializedName("location_type")
                               val locationType: String = "")


data class ConsolidatedWeatherItem(@SerializedName("visibility")
                                   val visibility: Double = 0.0,
                                   @SerializedName("created")
                                   val created: String = "",
                                   @SerializedName("applicable_date")
                                   val applicableDate: String = "",
                                   @SerializedName("wind_direction")
                                   val windDirection: Double = 0.0,
                                   @SerializedName("predictability")
                                   val predictability: Int = 0,
                                   @SerializedName("wind_direction_compass")
                                   val windDirectionCompass: String = "",
                                   @SerializedName("weather_state_name")
                                   val weatherStateName: String = "",
                                   @SerializedName("min_temp")
                                   val minTemp: Double = 0.0,
                                   @SerializedName("weather_state_abbr")
                                   val weatherStateAbbr: String = "",
                                   @SerializedName("the_temp")
                                   val theTemp: Double = 0.0,
                                   @SerializedName("humidity")
                                   val humidity: Double = 0.0,
                                   @SerializedName("wind_speed")
                                   val windSpeed: Double = 0.0,
                                   @SerializedName("id")
                                   val id: Long = 0,
                                   @SerializedName("max_temp")
                                   val maxTemp: Double = 0.0,
                                   @SerializedName("air_pressure")
                                   val airPressure: Double = 0.0)


data class SourcesItem(@SerializedName("crawl_rate")
                       val crawlRate: Int = 0,
                       @SerializedName("title")
                       val title: String = "",
                       @SerializedName("slug")
                       val slug: String = "",
                       @SerializedName("url")
                       val url: String = "")


