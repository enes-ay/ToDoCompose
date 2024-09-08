package com.example.todoapp.UIX

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.entity.Todo
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class TodoListViewmodel:ViewModel() {

    val todoList = MutableLiveData<List<Todo>>()

    fun getAllTodo(){

    }

    fun getSingleTodo(){

    }

}