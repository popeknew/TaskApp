package com.example.taskapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.taskapp.database.TaskDao
import com.example.taskapp.database.TaskDatabase
import com.example.taskapp.repository.TaskRepository
import com.example.taskapp.ui.MainActivityViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
    single { TaskRepository(get()) }
}

val viewModelModule = module {
    viewModel { MainActivityViewModel(get()) }
}

private fun provideDatabase(application: Application): TaskDatabase =
        Room.databaseBuilder(application, TaskDatabase::class.java, "task.db")
                .fallbackToDestructiveMigration()
                .addCallback(TaskDatabase.taskDatabaseCallback)
                .build()

private fun provideDao(database: TaskDatabase): TaskDao = database.taskDao()

fun startKoinImpl(context: Context) {
    startKoin {
        androidContext(context)
        androidLogger()
        modules(listOf(databaseModule, viewModelModule))
    }
}