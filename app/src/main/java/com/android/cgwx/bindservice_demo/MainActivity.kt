package com.android.cgwx.bindservice_demo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mService: MBindService
    private var mBound = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private var serviceConnection: ServiceConnection = object : ServiceConnection{
        override fun onServiceDisconnected(p0: ComponentName?) {
            mBound = false
        }

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            var binder: MBindService.MBinder = p1 as MBindService.MBinder
            mService = binder.service
            mBound = true
        }

    }

    fun bindService(view: View){
        bindService(Intent(this, MBindService::class.java), serviceConnection, Context.BIND_AUTO_CREATE)
    }

    fun unbindService(view: View){
        if (mBound){
            unbindService(serviceConnection)
            mBound = false
        }
    }

    fun getData(view: View){
        Log.d("TAG", "GETDATA")
        if(mBound){
            Toast.makeText(this,"get random:" + mService.getRandomNumber(), Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"unbind", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(mBound){
            unbindService(serviceConnection)
            mBound = false
        }
    }
}