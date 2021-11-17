package com.example.todolist.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.TasksScreenDirections
import com.example.todolist.model.Task
import com.google.android.material.card.MaterialCardView
import com.google.android.material.checkbox.MaterialCheckBox

class TasksAdapter(
    private val context: Context?,
    private val view: View?,
    private val dataSource: List<Task>
) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {


    class TaskViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        val listItemInRecyclerView: MaterialCardView =
            itemView.findViewById(R.id.list_item_in_recyclerView)
        val taskTitle: TextView? = itemView.findViewById(R.id.task_title_task_screen_textView)
        val taskDate: TextView? = itemView.findViewById(R.id.task_date_task_screen_textView)
        val isCompleted: MaterialCheckBox? = itemView.findViewById(R.id.isCompleted_checkBox)
        val isFavorite: MaterialCheckBox? = itemView.findViewById(R.id.isFavorite_checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        val item = dataSource[position]

        holder.taskTitle?.text = item.taskTitle
        holder.taskDate?.text = item.taskDate
        if (item.isCompleted) {
            holder.isCompleted?.isChecked = true
            holder.taskTitle?.paintFlags =
                holder.taskTitle?.paintFlags!!.or(Paint.STRIKE_THRU_TEXT_FLAG)
        }

        holder.isCompleted?.setOnClickListener {
            if (holder.isCompleted.isChecked) {
                item.isCompleted = true
                holder.taskTitle?.paintFlags =
                    holder.taskTitle?.paintFlags!!.or(Paint.STRIKE_THRU_TEXT_FLAG)

            } else {
                item.isCompleted = false
                holder.taskTitle?.paintFlags = 0

            }

        }

        if (item.isFavorite) holder.isFavorite?.isChecked = true

        holder.listItemInRecyclerView.setOnClickListener {
            val action = TasksScreenDirections.actionTasksScreenToTaskDetailsScreen(
                taskIndex = position,
                taskTitle = item.taskTitle,
                taskDate = item.taskDate,
                isCompleted = item.isCompleted,
                taskCreatedDate = item.taskCreatedDate,
                taskDateInMilliSeconds = item.taskDateInMelliSeconds,
                taskNote = item.taskNote

            )

            view?.findNavController()?.navigate(action)
        }

    }

    override fun getItemCount(): Int = dataSource.size


}


