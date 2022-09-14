package com.fa.workmanagerandservices.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.work.*
import com.fa.workmanagerandservices.workers.MyWorker
import java.util.concurrent.TimeUnit

class MyBackgroundService : Service() {
    //private lateinit var oneTimeWorkRequest: OneTimeWorkRequest
    private lateinit var periodicWorkRequest: PeriodicWorkRequest

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("Service", "Started")

        val workingCondition = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        /*oneTimeWorkRequest = OneTimeWorkRequestBuilder<MyWorker>()
            .setInitialDelay(10, TimeUnit.SECONDS)
            .setConstraints(workingCondition)
            .build()*/

        periodicWorkRequest = PeriodicWorkRequestBuilder<MyWorker>(15, TimeUnit.MINUTES)
            .setInitialDelay(10, TimeUnit.SECONDS)
            .setConstraints(workingCondition)
            .build()

        WorkManager.getInstance(this)
            .enqueue(periodicWorkRequest)

        return START_STICKY
    }

    /*override fun onDestroy() {
        super.onDestroy()
        Log.e("Service", "Stopped")
        WorkManager.getInstance(this)
            .cancelWorkById(periodicWorkRequest.id)
    }*/
}