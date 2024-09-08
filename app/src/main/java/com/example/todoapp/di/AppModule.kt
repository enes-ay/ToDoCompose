package com.example.todoapp.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.todoapp.data.datasource.TodoDatasource
import com.example.todoapp.data.repository.TodoRepository
import com.example.todoapp.room.TodoDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideTodoRepository(todoDS: TodoDatasource) : TodoRepository{
        return TodoRepository(todoDS)
    }

    @Provides
    @Singleton
    fun provideTodoDatasource(todoDAO: TodoDAO): TodoDatasource{
        return TodoDatasource(todoDAO)
    }

    @Provides
    @Singleton
    fun provideTodoDao(@ApplicationContext context: Context) : TodoDAO{
        val db = Room.databaseBuilder(context, Database::class.java,"todo.sqlite")
            .createFromFile("todo.sqlite").build()

        return db.getTodoDao()
    }
}