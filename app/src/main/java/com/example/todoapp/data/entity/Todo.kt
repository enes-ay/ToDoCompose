package com.example.todoapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "todoId")
    var id:Int,
    @ColumnInfo(name = "todoName")
    @NotNull
    var name:String,
    @ColumnInfo(name = "isDone")
    var isDone:Boolean
)
