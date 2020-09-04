package com.android.cgwx.bindservice_demo

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.util.*

class MBindService: Service() {
    private lateinit var binder: IBinder
    private lateinit var mGenerator: Random
    override fun onBind(p0: Intent?): IBinder? {
        return binder
    }

    class MBinder : Binder() {
        val service: MBindService
            get() = MBindService()
    }


    override fun onCreate() {
        Log.d("TAG", "onCreate");
        super.onCreate()
        binder = MBinder()
        mGenerator = Random()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d("TAG", "onUnbind");
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    fun getRandomNumber(): Int {
        return mGenerator.nextInt(100)
    }
}