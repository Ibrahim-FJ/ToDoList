package com.example.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.adapter.TasksAdapter
import com.example.todolist.databinding.CreateTaskBinding
import com.example.todolist.databinding.TasksScreenBinding
import com.example.todolist.model.Task
import com.example.todolist.viewmodel.TaskViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


class CreateTask : BottomSheetDialogFragment() {

    private var _binding: CreateTaskBinding? = null
    val binding get() = _binding
    var taskDate: String = ""
    private val taskViewModel: TaskViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


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

        binding?.addTaskCreateScreen?.setOnClickListener {
            addTask()
        }


        if(binding?.taskTitleCreateTaskScreenTextField?.text?.isNotEmpty() == false){
            Toast.makeText(this.requireContext(), "isNotEmpty", Toast.LENGTH_SHORT).show()
//            binding!!.addTaskCreateScreen.isClickable = false
//            binding!!.addTaskCreateScreen.setImageResource(R.drawable.ic_baseline_add_box_24)
        } else {
            Toast.makeText(this.requireContext(), "isEmpty", Toast.LENGTH_SHORT).show()
//            binding!!.addTaskCreateScreen.setImageResource(R.drawable.ic_baseline_add_box_24_blue)

        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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

    fun addTask() {
        if (binding?.taskTitleCreateTaskScreenTextField?.text?.isNotEmpty() == true){

            val taskTitle = binding?.taskTitleCreateTaskScreenTextField?.text.toString()
            val taskNote = binding?.addNoteCreateScreenTextField?.text.toString()
            val taskCreatedDate = convertMillisecondsToReadableDate(Calendar.getInstance().timeInMillis, "EEE, MMM d ")
            taskViewModel.addTask(Task(taskTitle, taskDate, taskNote, taskCreatedDate = taskCreatedDate))
            Toast.makeText(this.requireContext(), "Added Task", Toast.LENGTH_SHORT).show()
        }else {

        }


    }


}