package com.fa.workmanagerandservices.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(applicationContext: Context, workerParameters: WorkerParameters) : Worker(applicationContext, workerParameters) {
    override fun doWork(): Result {
        val a = 5
        val b = 20
        val total = a + b

        Log.e("Total", total.toString())
        return Result.success()
    }

}