package com.example.todoapp.UIX

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.navigation.NavController

@Composable
fun  TodoCreateScreen (navController: NavController) {
    var todoName by remember { mutableStateOf("") }

    Scaffold (modifier = Modifier.fillMaxSize()
    ) { paddingValues ->

            Column (modifier = Modifier.fillMaxSize().padding(paddingValues),
                verticalArrangement = Arrangement.Center){
                Column (modifier = Modifier
                    .fillMaxWidth().height(400.dp)
                    .padding(10.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment =Alignment.CenterHorizontally) {
                    TextField(value = todoName, onValueChange = {todoName = it})
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Save")
                    }


                }
            }
    }
}