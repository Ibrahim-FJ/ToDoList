package com.example.todolist.viewmodel

import android.content.Intent
import android.provider.AlarmClock
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.model.Task
import java.lang.Exception

class TaskViewModel : ViewModel() {

    private var _tasks: MutableLiveData<MutableList<Task>> =
        MutableLiveData(
            mutableListOf(
                Task(
                    "Android Development",
                    "thu, Nov, 18",
                    "Learning about Database"
                )
            )
        )
    val tasks get() = _tasks


    /**
     * Add task to the list
     */
    fun addTask(newTask: Task) {
        _tasks.value?.add(newTask)
    }

    /**
     * update task into the list
     * @param: index
     * @param: Task Model
     */
    fun updateTask(index: Int, updatedTask: Task) {
        try {
            _tasks.value?.set(index, updatedTask)

        } catch (e: Exception) {
            Log.d("TaskViewModel", e.message.toString())
        }
    }

    /**
     * remove task from the list at specific position
     * @param: index
     */

    fun removeTask(index: Int) {

        try {
            _tasks.value?.removeAt(index)
        } catch (e: Exception) {
            Log.d("TaskViewModel", e.message.toString())

        }

    }


}