package com.example.todoapp.UIX.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todoapp.R
import com.example.todoapp.UIX.viewmodel.TodoListViewmodel
import com.example.todoapp.data.entity.Todo
import com.example.todoapp.ui.theme.primaryColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(navController: NavController) {
    val todoListViewmodel: TodoListViewmodel = hiltViewModel()
    val todoList = todoListViewmodel.todoList.observeAsState(listOf())
    val snacBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val isSearching = remember { mutableStateOf(false) }
    val tf = remember { mutableStateOf("") }

    LaunchedEffect(key1 = true) {
        todoListViewmodel.getAllTodos()
    }
    Scaffold(
        topBar = { TopAppBar(
            title = {
                if (isSearching.value)
                {
                    TextField(value = tf.value,
                        onValueChange = {tf.value = it
                            todoListViewmodel.searchTodo(it)},
                        label = { Text(text = "Search")},
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            focusedLabelColor = Color.White,
                            focusedIndicatorColor = Color.White,
                            unfocusedLabelColor = Color.White
                        )
                    )
                }
                else {
                    Text(text = "Contacts")}
            },
            actions = {
                if (isSearching.value){
                    IconButton(onClick = { isSearching.value = false
                        tf.value =""
                    }) {
                        Icon(modifier = Modifier.size(20.dp),
                            imageVector = Icons.Default.Close
                            ,contentDescription = "search icon" )
                    }
                }
                else{
                    IconButton(onClick = {
                        isSearching.value = true
                    }) {
                        Icon(modifier = Modifier.size(20.dp),
                            imageVector = Icons.Default.Search
                            ,contentDescription = "search icon" )
                    }
                }
            }
        )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("creationScreen") },
                content = {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "")
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snacBarHostState) }
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
                 TodoItem(todo = item,
                     delete = {
                         scope.launch {
                             val sb = snacBarHostState.showSnackbar(
                                 message = "Are you sure to delete this todo?",
                                 actionLabel = "Yes",
                                 withDismissAction = true,
                                 duration = SnackbarDuration.Short,
                             )
                             if (sb == SnackbarResult.ActionPerformed){
                                 todoListViewmodel.deleteTodo(item.id)
                             }
                         }
                              },
                     onclick = { navController.navigate("detailScreen/${item.id}")
                 })

             }
            }
        }

    }

}

@Composable
fun TodoItem(todo: Todo, onclick: (Int) -> Unit, delete: (todoId:Int) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .background(primaryColor)
        .clickable {
            onclick(todo.id)
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
        Row (modifier = Modifier
            .fillMaxWidth()
            .weight(1f), horizontalArrangement = Arrangement.End) {
            Checkbox(
                modifier = Modifier.padding(end = 14.dp),
                checked = if (todo.isDone == 0) false else true,
                onCheckedChange = { todo.isDone = todo.isDone })
            IconButton(onClick = {
                delete(todo.id)
            }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "")
            }
        }

    }
}