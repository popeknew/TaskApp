package com.example.taskapp.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.taskapp.model.Task;

public class TaskRecyclerDiffUtilCallback {

    public static final DiffUtil.ItemCallback<Task> TASK_DIFF_CALLBACK = new DiffUtil.ItemCallback<Task>() {

        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.equals(newItem);
        }
    };
}