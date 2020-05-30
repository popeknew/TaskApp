package com.example.taskapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.os.Bundle;

import com.example.taskapp.R;
import com.example.taskapp.adapter.TaskRecyclerViewAdapter;
import com.example.taskapp.databinding.ActivityMainBinding;
import com.example.taskapp.model.Status;
import com.example.taskapp.model.Task;

import java.util.List;

import static org.koin.java.KoinJavaComponent.get;

public class MainActivity extends AppCompatActivity implements TaskRecyclerViewAdapter.StatusButtonClickedListener {

    private MainActivityViewModel viewModel = get(MainActivityViewModel.class);
    private ActivityMainBinding binding;
    private TaskRecyclerViewAdapter adapter = new TaskRecyclerViewAdapter(this);

    private Observer<List<Task>> allTasksFromDatabaseObserver = tasks -> {
        tasks.stream().filter(task -> task.getStatus() != Status.OPEN)
                .findFirst()
                .ifPresent(taskWithNoOpenStatus -> tasks.stream()
                        .filter(task -> task.getId() != taskWithNoOpenStatus.getId())
                        .forEach(task -> task.setStatusCanBeChanged(false)));
        adapter.submitList(tasks);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(this.viewModel);

        setUpRecyclerView();

        viewModel.getAllTasksFromDatabaseData().observe(this, allTasksFromDatabaseObserver);
    }

    private void setUpRecyclerView() {
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void changeStatusButtonClicked(Task task) {
        Task newTask = new Task(task.getName());
        newTask.setId(task.getId());
        switch (task.getStatus()) {
            case OPEN:
                newTask.setStatus(Status.TRAVELLING);
                break;
            case TRAVELLING:
                newTask.setStatus(Status.WORKING);
                break;
            case WORKING:
                newTask.setStatus(Status.OPEN);
                break;
        }
        viewModel.updateTask(newTask);
    }
}
