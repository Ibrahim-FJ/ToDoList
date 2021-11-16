package com.example.todolist.data

import com.example.todolist.model.Task


    var allTasks = mutableListOf (
        Task(

            "Shopping", "21/34/121", "Hello"
        ),
        Task(
            "Shoppingidnd", "21/34/121", "Ibrahim"
        ),
        Task(
            "Shoppinsdg", "21/34dd/121", "Alfaifi"
        ),

    )


object DataSource {
    fun loadDate(): MutableList<Task> {
        return allTasks
    }
//
//    fun add(task: LiveData<List<Task>>){
//        allTasks.add(task)
//    }

}