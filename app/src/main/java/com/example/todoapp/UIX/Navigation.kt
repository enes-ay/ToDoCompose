package com.example.todoapp.UIX

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(modifier: Modifier = Modifier, ) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "listScreen"){

        composable("listScreen"){
            TodoListScreen(navController = navController)

        }
        composable("detailScreen"){
            TodoDetailScreen(navController = navController)

        }
        composable("creationScreen"){
            TodoCreateScreen(navController =navController )

        }
    }

}