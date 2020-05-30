package com.example.taskapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.databinding.RowTaskBinding;
import com.example.taskapp.model.Task;
import com.example.taskapp.ui.MainActivity;
import com.example.taskapp.utils.TaskRecyclerDiffUtilCallback;

import java.util.List;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.TaskViewHolder> {

    private MainActivity mainActivity;

    public TaskRecyclerViewAdapter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RowTaskBinding binding = RowTaskBinding.inflate(inflater, parent, false);

        return new TaskViewHolder(binding);
    }

    private AsyncListDiffer<Task> differ = new AsyncListDiffer<Task>(this, TaskRecyclerDiffUtilCallback.TASK_DIFF_CALLBACK);

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.bind(differ.getCurrentList().get(position));
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        private RowTaskBinding binding;

        TaskViewHolder(@NonNull RowTaskBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Task task) {
            binding.setTask(task);
            binding.changeStatusButton.setOnClickListener(v -> {
                if (mainActivity != null) {
                    ((StatusButtonClickedListener) mainActivity).changeStatusButtonClicked(task);
                }
            });
        }
    }

    public void submitList(List<Task> newList) {
        differ.submitList(newList);
    }

    public interface StatusButtonClickedListener {
        void changeStatusButtonClicked(Task task);
    }
}
