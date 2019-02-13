package com.example.majdbaniodeh.rooms.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insertTask(TaskEntry taskEntity);

    @Query("select * from tasks")
    LiveData<List<TaskEntry>> getAllTasks();

    @Delete
    void deleteTask(TaskEntry taskEntry);
}
