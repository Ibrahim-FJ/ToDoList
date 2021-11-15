package com.example.todolist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.DataSource
import com.google.android.material.checkbox.MaterialCheckBox

class TasksAdapter(private val context: Context?) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder> () {
    private val dataSource = DataSource.task


    class TaskViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {


        val taskTitle: TextView? = itemView.findViewById(R.id.task_title_task_screen_textView)
        val taskDate: TextView? = itemView.findViewById(R.id.task_date_task_screen_textView)
        val isCompleted: MaterialCheckBox? = itemView.findViewById(R.id.isCompleted_checkBox)
        val isFavorite: MaterialCheckBox? = itemView.findViewById(R.id.isFavorite_checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val item = dataSource[position]
        println(holder.taskTitle)
        holder.taskTitle?.text = item.taskTitle
        holder.taskDate?.text = item.taskDate
        if(item.isCompleted) holder.isCompleted?.isChecked = true
        if(item.isFavorite) holder.isFavorite?.isChecked = true

    }

    override fun getItemCount(): Int = dataSource.size


}