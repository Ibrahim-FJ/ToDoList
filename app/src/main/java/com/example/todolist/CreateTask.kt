package com.example.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.todolist.databinding.CreateTaskBinding
import com.example.todolist.databinding.TasksScreenBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*


class CreateTask : BottomSheetDialogFragment() {

    private var _binding: CreateTaskBinding? = null
    val binding get() = _binding

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

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun showDatePicker() {

        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date").setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
        datePicker.show(parentFragmentManager, "DatePicker")
        datePicker.addOnPositiveButtonClickListener {

            convertMillisecondsToReadableDate(it)

        }

    }



    private fun convertMillisecondsToReadableDate (dateMilliseconds: Long): String{
        val format = SimpleDateFormat("EEE, MMM d ", Locale.getDefault())
        return format.format(Date(dateMilliseconds))
    }

}