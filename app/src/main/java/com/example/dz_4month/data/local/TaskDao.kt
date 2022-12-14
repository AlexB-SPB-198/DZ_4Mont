package com.example.dz_4month.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.dz_4month.Task

@Dao
interface TaskDao {
    @Insert
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)
@Query("SELECT * FROM task ORDER BY title ASC")
    fun getAllTask():List<Task>

}