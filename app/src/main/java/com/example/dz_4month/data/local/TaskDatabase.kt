package com.example.dz_4month.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dz_4month.Task

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase:RoomDatabase() {
    abstract fun dao(): TaskDao
}