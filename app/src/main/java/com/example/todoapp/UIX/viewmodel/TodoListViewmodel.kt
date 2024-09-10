package com.example.todoapp.UIX.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.entity.Todo
import com.example.todoapp.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewmodel @Inject constructor(var todoRepository: TodoRepository):ViewModel() {

    val todoList = MutableLiveData<List<Todo>>()

    init {
        getAllTodos()
    }

    fun getAllTodos(){
        CoroutineScope(Dispatchers.Main).launch {
           todoList.value = todoRepository.getAllTodos()
        }
    }

    fun deleteTodo(todoId: Int){
        CoroutineScope(Dispatchers.Main).launch {
            todoRepository.deleteTodo(todoId)
            getAllTodos()
        }
    }

    fun searchTodo(searchQuery: String){
        CoroutineScope(Dispatchers.Main).launch{
            todoList.value = todoRepository.searchTodo(searchQuery)
        }
    }

    fun updateDoneStatus(todo:Todo){
        CoroutineScope(Dispatchers.Main).launch {
            todoRepository.updateTodo(todo.id, todo.name, todo.isDone)
            getAllTodos()
        }
    }

}