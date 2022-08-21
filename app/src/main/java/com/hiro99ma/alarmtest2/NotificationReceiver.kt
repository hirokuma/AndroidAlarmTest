package com.hiro99ma.alarmtest2

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat

class NotificationReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "Receive !!")
        if (context == null) {
            Log.d(TAG, "null context")
            return
        }

        val builder = createNotification(context)
        val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(MainActivity.NOTIFY_TEST_ID, builder.build())
    }

    private fun createNotification(context: Context): NotificationCompat.Builder {
        // 自アプリを起こす
        val wakeupMeIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val wakeupMePendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, wakeupMeIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(context, MainActivity.NOTIFICATION_ID)
            .setSmallIcon(com.google.android.material.R.drawable.ic_clock_black_24dp)
            .setContentTitle("のてぃふぃけーしょんたいとる")
            .setContentText("のてぃふぃけーしょんてきすと")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(wakeupMePendingIntent)
            .setAutoCancel(true)
    }

    companion object {
        private val TAG = NotificationReceiver::class.java.name
    }
}