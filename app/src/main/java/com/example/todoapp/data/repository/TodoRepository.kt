package com.example.todoapp.data.repository

import androidx.room.Query
import com.example.todoapp.data.datasource.TodoDatasource
import com.example.todoapp.data.entity.Todo

class TodoRepository (var todoDS: TodoDatasource){
    suspend fun create(todoName: String) = todoDS.createTodo(todoName)

    suspend fun updateTodo(todoId: Int, todoName:String, isDone:Int) = todoDS.updateTodo(todoId, todoName, isDone)

    suspend fun deleteTodo(todoId: Int) = todoDS.deleteTodo(todoId)

    suspend fun getAllTodos() :List<Todo> = todoDS.getAllTodos()

    suspend fun searchTodo(searhQuery: String): List<Todo> = todoDS.searchTodo(searhQuery)

    suspend fun getTodo(todoId: Int): Todo = todoDS.getTodo(todoId)
}