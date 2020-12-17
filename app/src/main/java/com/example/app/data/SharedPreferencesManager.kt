package com.example.app.data

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.app.core.AppContext
import com.example.app.data.model.WeatherDataResponse
import com.google.gson.Gson
import javax.inject.Inject


class SharedPreferencesManager @Inject constructor(
        appContext: AppContext,
        private val gson: Gson
) {

    private val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            MasterKey.DEFAULT_MASTER_KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE)
            .build()

    private val masterKey = MasterKey.Builder(appContext.context)
            .setKeyGenParameterSpec(keyGenParameterSpec)
            .build()

    private val sharedPref = EncryptedSharedPreferences.create(
            appContext.context,
            KEY_SHARED_PREF_FILE,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)

    fun saveWeatherData(weatherDataResponse: WeatherDataResponse) {
        with(sharedPref.edit()) {
            putString(KEY_WEATHER_DATA, gson.toJson(weatherDataResponse))
            apply()
        }
    }

    fun loadWeatherData(): WeatherDataResponse? {
        return gson.fromJson(sharedPref.getString(KEY_WEATHER_DATA, null),
                WeatherDataResponse::class.java)
    }

    companion object {
        const val KEY_SHARED_PREF_FILE = "KEY_SHARED_PREF_FILE_EXAMPLE_APP"
        const val KEY_WEATHER_DATA = "KEY_WEATHER_DATA"
    }
}