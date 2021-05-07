package com.example.todolist.models

data class Todo (
    val todoTitle: String,
    var isDone: Boolean = false
)