package com.example.todolist


import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.adapter.TasksAdapter
import com.example.todolist.databinding.TasksScreenBinding
import com.example.todolist.viewmodel.TaskViewModel


class TasksScreen : Fragment() {

    private var _binding: TasksScreenBinding? = null
    val binding get() = _binding
    private val taskViewModel: TaskViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.tasks_screen, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding?.apply {

            taskScreenFragment = this@TasksScreen
            lifecycleOwner = viewLifecycleOwner
        }
        taskViewModel.tasks.observe(viewLifecycleOwner, {
            binding?.tasksRecyclerView?.adapter = TasksAdapter(this.context, view, it)
            binding?.tasksRecyclerView?.setHasFixedSize(true)
        })


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    fun goToCreateTask() {
        findNavController().navigate(R.id.action_tasksScreen_to_createTask)

    }

}