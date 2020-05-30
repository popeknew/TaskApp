package com.example.taskapp.utils;

import androidx.room.TypeConverter;

import com.example.taskapp.model.Status;
import com.google.gson.Gson;

public class MyTypeConverter {

    private Gson gson = new Gson();

    @TypeConverter
    public String statusToJson(Status status) {
        return gson.toJson(status);
    }

    @TypeConverter
    public Status jsonToStatus(String json) {
        return gson.fromJson(json, Status.class);
    }
}
