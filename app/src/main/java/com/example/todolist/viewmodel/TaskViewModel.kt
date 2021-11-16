package com.example.todolist.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.model.Task
import java.lang.Exception

class TaskViewModel : ViewModel() {

    private var _tasks: MutableLiveData<MutableList<Task>> =
        MutableLiveData(mutableListOf(Task("Ibrahim", "tue, 10, 21", "Hello")))
    val tasks get() = _tasks

    fun addTask(newTask: Task) {
        _tasks.value?.add(newTask)
    }

    fun updateTask(index: Int, updatedTask: Task) {
        try {
            _tasks.value?.set(index, updatedTask)

        }catch (e: Exception){
            Log.d("TaskViewModel", e.message.toString())
        }
    }

    fun removeTask(index: Int) {

        _tasks.value?.removeAt(index)
    }

}