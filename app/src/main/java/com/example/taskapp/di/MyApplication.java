package com.example.taskapp.di;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

       ModulesKt.startKoinImpl(getApplicationContext());
    }
}
