package com.example.taskapp.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.taskapp.model.Task;
import com.example.taskapp.repository.TaskRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private TaskRepository taskRepository;

    LiveData<List<Task>> getAllTasksFromDatabaseData() {
        return taskRepository.getAllTasks();
    }

    public MainActivityViewModel(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    void updateTask(Task task) {
        taskRepository.updateTask(task);
    }
}
