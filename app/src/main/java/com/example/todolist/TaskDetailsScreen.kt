package com.example.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.databinding.TaskDetailsScreenBinding
import com.example.todolist.model.Task
import com.example.todolist.viewmodel.TaskViewModel
import java.lang.Exception


class TaskDetailsScreen : Fragment() {
    private var _binding: TaskDetailsScreenBinding? = null
    val binding get() = _binding
    private val taskViewModel: TaskViewModel by activityViewModels ()
    private var taskIndexToUpdate: Int? = 0
    private var taskTitle: String? = ""
    private var taskDate: String? = ""
    private var taskIsCompleted: Boolean? = false
    private var taskNote: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.task_details_screen, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var taskCreatedDate = ""

        arguments.let {
            taskIndexToUpdate = it?.getInt("taskIndex")
            taskTitle = it?.getString("taskTitle")
            taskDate = it?.getString("taskDate")
            taskIsCompleted = it?.getBoolean("isCompleted")
            binding?.taskTitleDetailsScreenTextField?.setText(taskTitle)
            binding?.taskDateDetailsScreen?.setText(taskDate)
            binding?.isCompletedDetailsScreen?.isChecked = taskIsCompleted == true
            taskCreatedDate = it?.getString("taskCreatedDate").toString()

        }

        binding?.deleteTaskDetailsScreen?.setOnClickListener {
            removeTask(taskIndexToUpdate!!)
            findNavController().navigate(R.id.action_taskDetailsScreen_to_tasksScreen)
        }
        binding?.taskCreatedDateDetailsScreen?.text = taskCreatedDate

    }

    override fun onDestroy() {
        super.onDestroy()
        getTheTaskDetailsFromUI()
        taskViewModel.updateTask(taskIndexToUpdate?: 0, Task(taskTitle!!, taskDate!!, taskNote, isCompleted = taskIsCompleted!!))
        _binding = null
    }

    private fun getTheTaskDetailsFromUI(){
        taskTitle = binding?.taskTitleDetailsScreenTextField?.text.toString()
        taskNote = binding?.taskNoteDetailsScreenTextField?.text.toString()
        taskIsCompleted = binding?.isCompletedDetailsScreen?.isChecked
    }

    fun removeTask(index: Int) {
        taskViewModel.removeTask(index)
    }

}