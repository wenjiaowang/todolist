package com.example.todolist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.AddTaskFragment
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var taskTextView: TextView
    private lateinit var addTaskButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        taskTextView = findViewById(R.id.taskTextView)
        addTaskButton = findViewById(R.id.addTaskButton)

        addTaskButton.setOnClickListener {
            val fragment = AddTaskFragment()
            fragment.onTaskAdded = { taskName, priority, category ->
                taskTextView.text = "Task: $taskName\nPriority: $priority\nCategory: $category"
            }
            fragment.show(supportFragmentManager, "AddTaskFragment")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_add_task -> {
                val fragment = AddTaskFragment()
                fragment.onTaskAdded = { taskName, priority, category ->
                    taskTextView.text = "Task: $taskName\nPriority: $priority\nCategory: $category"
                }
                fragment.show(supportFragmentManager, "AddTaskFragment")
                true
            }
            R.id.menu_view_tasks -> {
                true
            }
            R.id.menu_clear -> {
                taskTextView.text = "No Task Now"
                true
            }
            R.id.menu_sort_tasks -> {
                true
            }
            R.id.menu_filter_tasks -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}