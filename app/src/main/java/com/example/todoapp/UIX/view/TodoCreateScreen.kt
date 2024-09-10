package com.example.todoapp.UIX.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todoapp.UIX.viewmodel.TodoCreateViewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  TodoCreateScreen (navController: NavController) {
    var todoName by remember { mutableStateOf("") }
    val todoCreateViewmodel : TodoCreateViewmodel = hiltViewModel()

    Scaffold (modifier = Modifier.fillMaxSize(),
        topBar = { CenterAlignedTopAppBar(title = { Text("Add new Todo") })}
    ) { paddingValues ->

            Column (modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
                verticalArrangement = Arrangement.Center){
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(10.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment =Alignment.CenterHorizontally) {
                    TextField(value = todoName, onValueChange = {todoName = it})
                    Button(onClick = {
                        todoCreateViewmodel.createTodo(todoName)
                    }) {
                        Text(text = "Save")
                    }
                }
            }
    }
}