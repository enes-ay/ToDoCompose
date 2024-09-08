package com.example.todoapp.data.datasource

import com.example.todoapp.data.entity.Todo
import com.example.todoapp.room.TodoDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoDatasource(var todoDao: TodoDAO) {

    suspend fun createTodo(todoName: String){
        val newTodo = Todo(0,todoName, false)
        todoDao.createTodo(newTodo)
    }
    suspend fun updateTodo(todoId:Int, todoName: String, isDone:Boolean){
        val updatedTodo = Todo(todoId,todoName, isDone)
        todoDao.updateTodo(updatedTodo)
    }
    suspend fun deleteTodo(todoId: Int){
        val deletedTodo = Todo(todoId,"",false)
        todoDao.deleteTodo(deletedTodo)
    }
    suspend fun getAllTodos(): List<Todo> = withContext(Dispatchers.IO){
        return@withContext todoDao.getAllTodos()
    }
    suspend fun searchTodo(searchQuery: String): List<Todo> = withContext(Dispatchers.IO){
        return@withContext todoDao.searchTodo(searchQuery)
    }
}