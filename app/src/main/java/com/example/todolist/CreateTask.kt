package com.example.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.todolist.databinding.CreateTaskBinding
import com.example.todolist.model.Task
import com.example.todolist.viewmodel.TaskViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


class CreateTask : BottomSheetDialogFragment() {

    private var _binding: CreateTaskBinding? = null
    val binding get() = _binding
    var taskDateInMelliSeconds: Long = 0
    var taskDate: String = ""
    private val taskViewModel: TaskViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.create_task, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding?.apply {
            createTaskFragment = this@CreateTask
            lifecycleOwner = viewLifecycleOwner
        }


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    fun addTask() {
        if (binding?.taskTitleCreateTaskScreenTextField?.text?.isNotEmpty() == true) {

            val taskTitle = binding?.taskTitleCreateTaskScreenTextField?.text.toString()
            val taskNote = binding?.addNoteCreateScreenTextField?.text.toString()
            val taskCreatedDate = convertMillisecondsToReadableDate(
                Calendar.getInstance().timeInMillis,
                "EEE, MMM d "
            )
            taskViewModel.addTask(
                Task(
                    taskTitle,
                    taskDate,
                    taskNote,
                    taskCreatedDate = taskCreatedDate,
                    taskDateInMelliSeconds = taskDateInMelliSeconds
                )
            )
            Toast.makeText(this.requireContext(), "Added Task", Toast.LENGTH_SHORT).show()
            binding!!.taskTitleCreateTaskScreenTextField.clearFocus()
        }

    }


    /**
     * To display the date picker on the screen
     */
    fun showDatePicker() {

        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date").setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()


        datePicker.show(parentFragmentManager, "DatePicker")
        datePicker.addOnPositiveButtonClickListener {
            taskDateInMelliSeconds = it
            taskDate = convertMillisecondsToReadableDate(it, "EEE, MMM d ")

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