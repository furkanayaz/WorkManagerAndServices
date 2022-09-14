package com.fa.workmanagerandservices

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.fa.workmanagerandservices.databinding.ActivityMainBinding
import com.fa.workmanagerandservices.services.MyBackgroundService
import com.fa.workmanagerandservices.workers.MyWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.activityMainObject = this

    }

    fun run() {
        //startService(Intent(this, MyBackgroundService::class.java))

        val periodicWorkRequest = PeriodicWorkRequestBuilder<MyWorker>(15, TimeUnit.MINUTES)
            .setInitialDelay(10, TimeUnit.SECONDS)
            .build()

        WorkManager.getInstance(this)
            .enqueue(periodicWorkRequest)

        WorkManager.getInstance(this)
            .getWorkInfoByIdLiveData(periodicWorkRequest.id)
            .observe(this) {
                Log.e("Work State", it.state.name)
            }

    }

    fun stop() {
        stopService(Intent(this, MyBackgroundService::class.java))
    }

}