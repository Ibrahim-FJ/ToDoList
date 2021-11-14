package com.example.todolist.model

import java.util.*

data class Task(
                var taskTitle: String,
                var taskDate: Date,
                var isRead: Boolean,
                var isFavorite: Boolean
                )


