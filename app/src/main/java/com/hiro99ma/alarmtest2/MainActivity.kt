package com.hiro99ma.alarmtest2

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log

class MainActivity : AppCompatActivity() {
    private var alarmStarted = false
    private val alarmInterval: Long = 15 * 60 * 1000 // 15min
    private var pendingIntent: PendingIntent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
    }
}