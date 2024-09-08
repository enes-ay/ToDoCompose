package com.example.todoapp.UIX

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todoapp.UIX.view.TodoCreateScreen
import com.example.todoapp.UIX.view.TodoDetailScreen
import com.example.todoapp.UIX.view.TodoListScreen

@Composable
fun Navigation(modifier: Modifier = Modifier, ) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "listScreen"){

        composable("listScreen"){
            TodoListScreen(navController = navController)

        }
        composable("detailScreen/{todoId}", arguments = listOf(
            navArgument("todoId"){
                type= NavType.IntType
            }
        )){
            val todoId = it.arguments?.getInt("todoId")
            TodoDetailScreen(navController = navController, todoId = todoId)

        }
        composable("creationScreen"){
            TodoCreateScreen(navController =navController )

        }
    }

}