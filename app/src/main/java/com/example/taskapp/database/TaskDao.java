package com.example.taskapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.taskapp.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query(value = "SELECT * FROM task_table")
    LiveData<List<Task>> getAll();

    @Insert
    void insertAll(Task... tasks);

    @Update
    void updateTask(Task task);
}
