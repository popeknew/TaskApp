package com.example.taskapp.adapter;

import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

import com.example.taskapp.R;
import com.example.taskapp.model.Status;
import com.google.android.material.button.MaterialButton;

public class MyBindingAdapter {

    @BindingAdapter("setBackgroundColor")
    public static void changeBackground(View view, Status status) {
        switch (status) {
            case OPEN:
                view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.status_open_background_color));
                break;
            case TRAVELLING:
                view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.status_traveling_background_color));
                break;
            case WORKING:
                view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.status_working_background_color));
        }
    }

    @BindingAdapter("setButtonText")
    public static void setButtonText(MaterialButton button, Status status) {
        switch (status) {
            case OPEN:
                button.setText(R.string.row_task_change_status_button_start_travel_text);
                break;
            case WORKING:
                button.setText(R.string.row_task_change_status_button_stop_text);
                break;
            case TRAVELLING:
                button.setText(R.string.row_task_change_status_button_start_work_text);
        }
    }

    @BindingAdapter("setVisibility")
    public static void setVisibility(MaterialButton button, boolean visible) {
        if (visible) {
            button.setVisibility(View.VISIBLE);
        } else {
            button.setVisibility(View.INVISIBLE);
        }
    }
}
