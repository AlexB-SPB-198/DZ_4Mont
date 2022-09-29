package com.example.dz_4month

import android.app.Application
import androidx.room.Room
import com.example.dz_4month.data.local.TaskDatabase


class App:Application() {
    override fun onCreate() {
        super.onCreate()
        db=Room.databaseBuilder(this,TaskDatabase::class.java,"database").allowMainThreadQueries()
            .build()
    }
    companion object{
        public lateinit var db:TaskDatabase
    }
}