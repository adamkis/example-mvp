package com.example.app.feature.websiteFetch

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.app.R
import com.example.app.data.api.WeatherApi
import com.example.app.feature.weather.WeatherActivity
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MyForegroundService : Service() {

    @Inject
    lateinit var weatherApi: WeatherApi

    private val subscriptions = CompositeDisposable()
    private lateinit var mediaPlayer: MediaPlayer
    private var savedWebsiteString: String = ""
    private lateinit var notificationBuilder: NotificationCompat.Builder
    private lateinit var notificationManager: NotificationManager
    private var counter = 1

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(applicationContext, R.raw.sound)
        mediaPlayer.isLooping = false
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val input = intent?.getStringExtra(inputExtra)

        val notificationIntent = Intent(this, WeatherActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

        val myChannelId =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    createNotificationChannel(channelID, "My Background Service")
                } else {
                    // If earlier version channel ID is not used
                    // https://developer.android.com/reference/android/support/v4/app/NotificationCompat.Builder.html#NotificationCompat.Builder(android.content.Context)
                    ""
                }

        notificationBuilder = NotificationCompat.Builder(this, myChannelId)
                .setContentTitle(foregroundServiceNotificationTitle)
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)

        val notification = notificationBuilder.build()

        startForeground(notificationId, notification)

        startRepeatedDownload()

        return START_NOT_STICKY
    }

    private fun startRepeatedDownload() {
        Observable.interval(5, TimeUnit.SECONDS)
                .subscribe {
                    Log.d("xzxz", "test1")
                    loadWeatherData()
                }
    }

    private fun loadWeatherData() {
        val subscribe = weatherApi.getWebsite()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val responseString = it.string()
                    Log.d("xzxz", responseString)
                    Toast.makeText(applicationContext, responseString, Toast.LENGTH_SHORT).show()
                    alertForChange(responseString)
                    updateNotification()
                }, {
                    it.printStackTrace()
                })
        subscriptions.add(subscribe)
    }

    private fun alertForChange(websiteString: String) {
        if (savedWebsiteString.isEmpty()) {
            savedWebsiteString = websiteString
        }
        if (savedWebsiteString != websiteString) {
            mediaPlayer.start()
        }
    }

    private fun updateNotification() {
        notificationBuilder.setContentTitle("${++counter}")
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String {
        val chan = NotificationChannel(channelId,
                channelName, NotificationManager.IMPORTANCE_NONE)
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }

    override fun onDestroy() {
        subscriptions.clear()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    companion object {

        const val notificationId = 428367

        // Intent Constants
        const val inputExtra = "inputExtra"

        // Notification Constants
        const val channelID = "myServiceChannel"
        const val foregroundServiceNotificationTitle = "My Foreground Services"
        const val foregroundIntentServiceNotificationTitle = "My Foreground Intent Service"
        const val notificationChannelName = "My Service Channel"

        // Job scheduler Constants
        const val jobId = 123
    }
}