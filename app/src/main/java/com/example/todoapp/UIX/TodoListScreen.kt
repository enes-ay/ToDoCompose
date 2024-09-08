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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todoapp.R
import com.example.todoapp.data.entity.Todo
import com.example.todoapp.ui.theme.primaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(navController: NavController) {
    val todoListViewmodel: TodoListViewmodel = hiltViewModel()

    val todoList = todoListViewmodel.todoList.observeAsState(listOf())

    LaunchedEffect(key1 = true) {
        todoListViewmodel.getAllTodos()
    }
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text(text = "To Do List") }) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("creationScreen") },
                content = {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "")
                }
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(todoList.value.count())
            {
             Column (modifier = Modifier
                 .fillMaxSize()
                 .padding(vertical = 10.dp, horizontal = 10.dp)){
                 val item = todoList.value[it]
                 TodoItem(todo = item)
             }
            }
        }

    }

}

@Composable
fun TodoItem(todo: Todo) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .background(primaryColor)
        .clickable {
        }
        .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            modifier = Modifier.weight(3f),
            text = todo.name,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Row (modifier = Modifier.fillMaxWidth().weight(1f), horizontalArrangement = Arrangement.End) {
            Checkbox(
                modifier = Modifier.padding(end = 14.dp),
                checked = if (todo.isDone == 0) false else true,
                onCheckedChange = { todo.isDone = 0 })
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "")
            }
        }

    }
}