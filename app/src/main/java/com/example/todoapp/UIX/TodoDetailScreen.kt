package com.example.todoapp.UIX

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(navController: NavController) {

    Scaffold (topBar = { CenterAlignedTopAppBar(title = { Text("To Do Detail") })}) { paddingValues ->
        Column (modifier = Modifier.padding(paddingValues)) {
            Text(text = "")
        }

    }
}