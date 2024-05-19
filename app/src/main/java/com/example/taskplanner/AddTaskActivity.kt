package com.example.taskplanner

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taskplanner.databinding.ActivityAddTaskBinding

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var db: TasksDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TasksDatabaseHelper(this)

        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString().trim()
            val content = binding.contentEditText.text.toString().trim()

            if (validateInput(title, content)) {
                val task = Task(0, title, content)
                db.insertTask(task)
                Toast.makeText(this, "Task saved", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun validateInput(title: String, content: String): Boolean {
        return when {
            title.isEmpty() -> {
                binding.titleEditText.error = "Title cannot be empty"
                false
            }
            content.isEmpty() -> {
                binding.contentEditText.error = "Description cannot be empty"
                false
            }
            else -> true
        }
    }
}
