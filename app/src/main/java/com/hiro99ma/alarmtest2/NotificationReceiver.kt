package com.hiro99ma.alarmtest2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NotificationReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "Receive !!")
    }

    companion object {
        private val TAG = NotificationReceiver::class.java.name
    }
}