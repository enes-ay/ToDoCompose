package com.example.todoapp.UIX

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapp.R
import com.example.todoapp.data.entity.Todo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(navController: NavController) {

    val todoList = remember { mutableStateListOf<Todo>() }
    val todoIsDone = remember { mutableStateListOf(false) }

    LaunchedEffect(key1 = true) {
        val todo1 = Todo(1,"232",false)
        val todo2 = Todo(1,"sdg",false)
        val todo3 = Todo(1,"2sdhsdh32",false)
        val todo4 = Todo(1,"dsh",false)
        val todo5 = Todo(1,"23dshsd2",false)
        val todo6 = Todo(1,"dsgsh",false)
        val todo7 = Todo(1,"232",false)
        todoList.add(todo1)
        todoList.add(todo2)
        todoList.add(todo3)
        todoList.add(todo4)
        todoList.add(todo5)
        todoList.add(todo6)
        todoList.add(todo7)
        todoList.add(todo7)
        todoList.add(todo7)
    }
    Scaffold (
        topBar = { CenterAlignedTopAppBar(title = { Text(text = "To Do List")})},
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("creationScreen") },
                content = {
                    Icon(imageVector =Icons.Default.Add, contentDescription = "")
                }
            )
        }
    ) { paddingValues ->

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            items(todoList.count())
            {
             Column (modifier = Modifier
                 .fillMaxSize()
                 .padding(vertical = 10.dp, horizontal = 10.dp)){
                 val item = todoList[it]
                 TodoItem(todo = item)
             }
            }
        }

    }
    
}

@Composable
fun TodoItem(todo: Todo) {
    Row(modifier= Modifier
        .fillMaxWidth()
        .height(60.dp)
        .background(Color.Green)
        .clickable {
        }
        .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = "${todo.name}", fontSize = 21.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Checkbox(checked = todo.isDone, onCheckedChange = { todo.isDone = false} )
    }
}