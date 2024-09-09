package com.example.todoapp.UIX.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todoapp.UIX.viewmodel.TodoDetailViewmodel
import com.example.todoapp.data.entity.Todo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(navController: NavController, todoId:Int?) {
    val todoDetailViewmodel : TodoDetailViewmodel = hiltViewModel()
    val todo = todoDetailViewmodel.todoDetail.observeAsState()
    val todoNameTf= remember { mutableStateOf("") }

    LaunchedEffect(key1 = true) {
        Log.e("id",todoId.toString())
        if (todoId!=null){
            todoDetailViewmodel.getTodo(todoId)
        }
    }

    LaunchedEffect(key1 = todo.value) {
        todo.value?.let {
            todoNameTf.value = it.name
        }
    }

    Scaffold (topBar = { CenterAlignedTopAppBar(title = { Text("To Do Detail") })}) { paddingValues ->
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(value = todoNameTf.value , onValueChange = { todoNameTf.value=it })
            Button(
                modifier = Modifier.padding(top = 20.dp),
                onClick = {
                todo.value?.let {
                    val newTodo = Todo(todo.value!!.id,todoNameTf.value, todo.value!!.isDone)
                    todoDetailViewmodel.updateTodo(newTodo)
                }
            }) {
                Text("Update")
            }
        }

    }
}