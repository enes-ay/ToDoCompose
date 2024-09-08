package com.example.todoapp.UIX

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todoapp.data.entity.Todo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(navController: NavController, todoId:Int?) {
    val todoDetailViewmodel :TodoDetailViewmodel = hiltViewModel()
    val todo = todoDetailViewmodel.todoDetail.observeAsState()

    LaunchedEffect(key1 = true) {
        Log.e("id",todoId.toString())
        if (todoId!=null){
            todoDetailViewmodel.getTodo(todoId)
        }
    }

    Scaffold (topBar = { CenterAlignedTopAppBar(title = { Text("To Do Detail") })}) { paddingValues ->
        Column (modifier = Modifier.padding(paddingValues)) {
            Text(text = todo.value?.name ?: "No title")
        }

    }
}