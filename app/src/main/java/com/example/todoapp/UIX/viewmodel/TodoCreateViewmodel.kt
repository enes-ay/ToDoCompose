package com.example.todoapp.UIX.viewmodel

import androidx.lifecycle.ViewModel
import com.example.todoapp.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoCreateViewmodel @Inject constructor(val todoRepository: TodoRepository):ViewModel() {

    fun createTodo(todoName:String){
        CoroutineScope(Dispatchers.Main).launch{
            todoRepository.create(todoName)
        }
    }
}
