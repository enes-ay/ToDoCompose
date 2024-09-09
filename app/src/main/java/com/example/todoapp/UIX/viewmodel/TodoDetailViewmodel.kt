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
class TodoDetailViewmodel  @Inject constructor(var todoRepository: TodoRepository) :ViewModel() {

    val todoDetail = MutableLiveData<Todo>()

    fun getTodo(todoId:Int){
        CoroutineScope(Dispatchers.Main).launch {
            todoDetail.value= todoRepository.getTodo(todoId)
        }
    }
    fun updateTodo(todo : Todo?){
        CoroutineScope(Dispatchers.Main).launch {
            if(todo!=null){
                todoRepository.updateTodo(todo.id,todo.name,todo.isDone)
            }
        }
    }
}