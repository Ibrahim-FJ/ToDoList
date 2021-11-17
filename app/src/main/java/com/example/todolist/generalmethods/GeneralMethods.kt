package com.example.todolist.generalmethods

import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class GeneralMethods {
    var taskDateInMelliSeconds: Long = 0

    /**
     * To display the date picker on the screen
     */
    fun showDatePicker(parentFragmentManager: FragmentManager) {

        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date").setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()


        datePicker.show(parentFragmentManager , "DatePicker")
        datePicker.addOnPositiveButtonClickListener {
            taskDateInMelliSeconds = it
            convertMillisecondsToReadableDate(it, "EEE, MMM d ")

        }

    }


    /**
     * function to convert date in milliseconds to readable date at the format "EEE, MMM d ", for example "Tue, Sep 10 "
     * @param dateMilliseconds: Long
     * @param datePattern: String
     * @return formattedDate: String
     */


    fun convertMillisecondsToReadableDate(
        dateMilliseconds: Long,
        datePattern: String
    ): String {
        val format = SimpleDateFormat(datePattern, Locale.getDefault())
        return format.format(Date(dateMilliseconds))
    }


}