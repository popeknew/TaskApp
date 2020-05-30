package com.example.taskapp.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.taskapp.model.Task;

import java.util.concurrent.Executors;

import static org.koin.java.KoinJavaComponent.get;

@Database(entities = Task.class, version = 5)
public abstract class TaskDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();

    public static RoomDatabase.Callback taskDatabaseCallback = new RoomDatabase.Callback() {
        private String taskName = "Task Example";
        private int openDatabaseTasksAmount = 20;

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            Executors.newSingleThreadExecutor().execute(() -> {
                TaskDao taskDao = get(TaskDao.class);

                for (int i = 0; i < openDatabaseTasksAmount; i++) {
                    taskDao.insertAll(new Task(taskName + i));
                }
            });
        }
    };
}
