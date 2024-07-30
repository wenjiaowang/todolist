package com.example.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.fragment.app.DialogFragment

class AddTaskFragment : DialogFragment() {

    private lateinit var taskEditText: EditText
    private lateinit var priorityRadioGroup: RadioGroup
    private lateinit var categorySpinner: Spinner
    private lateinit var saveButton: Button

    var onTaskAdded: ((String, String, String) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)

        taskEditText = view.findViewById(R.id.taskEditText)
        priorityRadioGroup = view.findViewById(R.id.priorityRadioGroup)
        categorySpinner = view.findViewById(R.id.categorySpinner)
        saveButton = view.findViewById(R.id.saveButton)

        val categories = arrayOf("Work", "Study", "Entertainment")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = adapter

        saveButton.setOnClickListener {
            val taskName = taskEditText.text.toString()
            val priority = if (priorityRadioGroup.checkedRadioButtonId == R.id.highPriorityRadioButton) {
                "High Priority"
            } else {
                "Low Priority"
            }
            val category = categorySpinner.selectedItem.toString()
            onTaskAdded?.invoke(taskName, priority, category)
            dismiss()
        }

        return view
    }
}