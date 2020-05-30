package com.example.taskapp.repository;

import androidx.lifecycle.LiveData;

import com.example.taskapp.database.TaskDatabase;
import com.example.taskapp.model.Task;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskRepository {

    private TaskDatabase taskDatabase;

    public TaskRepository(TaskDatabase taskDatabase) {
        this.taskDatabase = taskDatabase;
    }

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public LiveData<List<Task>> getAllTasks() {
        return taskDatabase.taskDao().getAll();
    }

    public void updateTask(Task task) {
        executorService.submit(() -> taskDatabase.taskDao().updateTask(task));
    }
}
