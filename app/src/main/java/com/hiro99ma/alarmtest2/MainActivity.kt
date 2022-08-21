package com.hiro99ma.alarmtest2

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var alarmStarted = false
    private val alarmInterval: Long = 3 * 60 * 1000 // 3min
    private var pendingIntent: PendingIntent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val name = "のてぃふぃけーしょんちゃんねる"
        val descriptionText = "のてぃふぃけーしょんちゃんねるのですくりぷしょん"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(NOTIFICATION_ID, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    override fun onResume() {
        super.onResume()

        if (!alarmStarted) {
            setAlarm(applicationContext)
            alarmStarted = true
        }
    }

    private fun setAlarm(context: Context) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val actionIntent = Intent(context, NotificationReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(
            this, 0, actionIntent, PendingIntent.FLAG_IMMUTABLE)

        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + alarmInterval,
            alarmInterval,
            pendingIntent)

        Log.d(TAG, "setAlarm")
    }

    private fun cancelAlarm() {
        if (pendingIntent != null) {
            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(pendingIntent)
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.name

        const val NOTIFICATION_ID = "10000"
        const val NOTIFY_TEST_ID = 10000
    }
}