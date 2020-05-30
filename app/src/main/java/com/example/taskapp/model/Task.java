package com.example.taskapp.model;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.taskapp.utils.MyTypeConverter;

@Entity(tableName = "task_table")
public class Task {

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "status")
    @TypeConverters(MyTypeConverter.class)
    private Status status;

    @Ignore
    private Boolean statusCanBeChanged;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public Task(String name) {
        this.name = name;
        this.status = Status.OPEN;
        this.statusCanBeChanged = true;
    }

    public void setStatus(Status newStatus) {
        this.status = newStatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public boolean isStatusCanBeChanged() {
        return statusCanBeChanged;
    }

    public void setStatusCanBeChanged(Boolean statusCanBeChanged) {
        this.statusCanBeChanged = statusCanBeChanged;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Task) {
            Task other = (Task) obj;
            if (other.id != this.id) {
                return false;
            }
            if (other.status != this.status) {
                return false;
            }
            if (!other.name.equals(this.name)) {
                return false;
            }
            return other.statusCanBeChanged == this.statusCanBeChanged;
        } else {
            return false;
        }
    }
}
