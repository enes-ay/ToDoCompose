package com.example.todoapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.data.entity.Todo

@Dao
interface TodoDAO {

    @Query("SELECT * FROM todo")
    suspend fun getAllTodos(): List<Todo>

    @Query("SELECT * FROM todo WHERE todoId=:todoId")
    suspend fun getTodo(todoId:Int): Todo

    @Insert
    suspend fun createTodo(todo: Todo)

    @Update
    suspend fun updateTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM todo WHERE todoName like '%'||:searchQuery||'%'")
    suspend fun searchTodo(searchQuery:String) : List<Todo>
}