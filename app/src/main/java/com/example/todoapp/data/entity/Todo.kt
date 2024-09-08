package com.example.todoapp.data.entity

import androidx.room.Entity

@Entity(tableName = "todos")
data class Todo(
    var id:Int,
    var name:String,
    var isDone:Boolean
)
