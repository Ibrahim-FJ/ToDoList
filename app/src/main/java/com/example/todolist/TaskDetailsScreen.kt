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
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


class TaskDetailsScreen : Fragment() {
    private var _binding: TaskDetailsScreenBinding? = null
    val binding get() = _binding
    private val taskViewModel: TaskViewModel by activityViewModels()
    private var taskIndexToUpdate: Int? = 0
    private var taskTitle: String? = ""
    private var taskDate: String? = ""
    private var taskIsCompleted: Boolean? = false
    private var taskNote: String = ""
    private var taskDateInMilliSeconds: Long = 0
    private var taskCreatedDate: String = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.task_details_screen, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments.let {
            // Receiving the arguments from the TasksAdapter

            taskIndexToUpdate = it?.getInt("taskIndex")
            taskTitle = it?.getString("taskTitle")
            taskDate = it?.getString("taskDate")
            taskIsCompleted = it?.getBoolean("isCompleted")
            taskNote = it?.getString("taskNote")!!
            taskCreatedDate = it.getString("taskCreatedDate")!!
            taskDateInMilliSeconds = it.getLong("taskDateInMilliSeconds")

        }

        binding?.taskTitleDetailsScreenTextField?.setText(taskTitle)
        binding?.taskDateDetailsScreen?.text = taskDate
        binding?.taskNoteDetailsScreenTextField?.setText(taskNote)
        binding?.isCompletedDetailsScreen?.isChecked = taskIsCompleted == true
        checkTaskDatePassed(taskDateInMilliSeconds)
        binding?.taskCreatedDateDetailsScreen?.text = taskCreatedDate
        binding?.taskDateDetailsScreen?.text = taskDate

        binding?.deleteTaskDetailsScreen?.setOnClickListener {
            removeTask(taskIndexToUpdate!!)
            findNavController().navigate(R.id.action_taskDetailsScreen_to_tasksScreen)
        }

        binding?.editTaskDateTaskDetailsScreen?.setOnClickListener {
            showDatePicker()
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        // Retrieve the updated data about the task from the UI elements.
        getTheTaskDetailsFromUI()
        // Update the viewModel with the new data retrieved from the UI elements.
        taskViewModel.updateTask(
            taskIndexToUpdate ?: 0,
            Task(
                taskTitle!!,
                taskDate!!,
                taskNote,
                isCompleted = taskIsCompleted!!,
                taskDateInMelliSeconds = taskDateInMilliSeconds,
                taskCreatedDate = taskCreatedDate
            )
        )
        _binding = null
    }


    /**
     * Function to retrieve the details from the UI to store them into the Task
     */

    private fun getTheTaskDetailsFromUI() {
        taskTitle = binding?.taskTitleDetailsScreenTextField?.text.toString()
        taskNote = binding?.taskNoteDetailsScreenTextField?.text.toString()
        taskIsCompleted = binding?.isCompletedDetailsScreen?.isChecked
    }


    /**
     * Function to remove task from the list
     * @param: index
     */
    private fun removeTask(index: Int) {
        taskViewModel.removeTask(index)
    }

    /**
     * Function to check if task date has been passed
     */
    private fun checkTaskDatePassed(taskDateInMilliSeconds: Long) {
        if (Calendar.getInstance().timeInMillis > taskDateInMilliSeconds) {
            binding?.dataPassedMsgDetailsScreen?.visibility = View.VISIBLE
            binding?.dataPassedMsgDetailsScreen?.text = getString(R.string.task_date_comparsion)
        }

    }

    /**
     * To display the date picker on the screen
     */
    private fun showDatePicker() {

        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date").setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        datePicker.show(parentFragmentManager, "DatePicker")
        datePicker.addOnPositiveButtonClickListener {
            taskDateInMilliSeconds = it
            taskDate = convertMillisecondsToReadableDate(it, "EEE, MMM d ")
            binding?.taskDateDetailsScreen?.text = taskDate


        }

    }


    /**
     * function to convert date in milliseconds to readable date at the format "EEE, MMM d ", for example "Tue, Sep 10 "
     * @param dateMilliseconds: Long
     * @param datePattern: String
     * @return formattedDate: String
     */

    private fun convertMillisecondsToReadableDate(
        dateMilliseconds: Long,
        datePattern: String
    ): String {
        val format = SimpleDateFormat(datePattern, Locale.getDefault())
        return format.format(Date(dateMilliseconds))
    }

}