package com.example.taskplanner

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taskplanner.databinding.ActivityUpdateTaskBinding

class UpdateTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateTaskBinding
    private lateinit var db: TasksDatabaseHelper
    private var taskId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TasksDatabaseHelper(this)

        taskId = intent.getIntExtra("task_id", -1)
        if (taskId == -1) {
            finish()
            return
        }

        val task = db.getTaskByID(taskId)
        binding.updateTitleEditText.setText(task.title)
        binding.UpdateContentEditText.setText(task.content)

        binding.updateSaveButton.setOnClickListener {
            val newTitle = binding.updateTitleEditText.text.toString().trim()
            val newContent = binding.UpdateContentEditText.text.toString().trim()

            if (validateInput(newTitle, newContent)) {
                val updateTask = Task(taskId, newTitle, newContent)
                db.updateTask(updateTask)
                Toast.makeText(this, "Changes Saved.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun validateInput(title: String, content: String): Boolean {
        return when {
            title.isEmpty() -> {
                binding.updateTitleEditText.error = "Title cannot be empty"
                false
            }
            content.isEmpty() -> {
                binding.UpdateContentEditText.error = "Description cannot be empty"
                false
            }
            else -> true
        }
    }
}
