package com.example.todolist.model


data class Task(
    var taskTitle: String = "",
    var taskDate: String = "",
    var taskNote: String = "",
    var taskCreatedDate: String = "",
    var isCompleted: Boolean = false,
    var isFavorite: Boolean = false

)


