package com.example.servicetest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class MyService : Service() {

    private val mBinder = DownloadBinder()

    class DownloadBinder : Binder() {

        fun startDownload() {
            Log.d(TAG, "startDownload executed")
        }

        fun getProgress(): Int {
            Log.d(TAG, "getProgress executed")
            return 0
        }

    }

    companion object {
        private const val TAG = "MyService"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate executed")
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("my_service", "前台Service通知",
                NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val intent = Intent(this, MainActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(this, "my_service")
            .setContentTitle("This is content title")
            .setContentText("This is content text")
            .setSmallIcon(androidx.appcompat.R.drawable.abc_btn_radio_material)
            .setLargeIcon(BitmapFactory.decodeResource(resources, androidx.constraintlayout.widget.R.drawable.abc_btn_radio_material))
            .setContentIntent(pi)
            .build()
        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand executed")

        val test = intent?.getBooleanExtra("test", false)
        Log.d(TAG, "Intent test: $test")

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy executed")
    }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }
}