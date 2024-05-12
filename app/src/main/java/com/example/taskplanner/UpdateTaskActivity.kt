package com.example.taskplanner

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.taskplanner.databinding.ActivityUpdateTaskBinding


class UpdateTaskActivity : AppCompatActivity() {

    private lateinit var binding:ActivityUpdateTaskBinding
    private lateinit var db: TasksDatabaseHelper
    private var taskId:Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TasksDatabaseHelper(this)

        taskId = intent.getIntExtra("task_id",-1)
        if(taskId == -1){
            finish()
            return
        }

        val task = db.getTaskByID(taskId)
        binding.updateTitleEditText.setText(task.title)
        binding.UpdateContentEditText.setText(task.content)

        binding.updateSaveButton.setOnClickListener{
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.UpdateContentEditText.text.toString()
            val updateTask = Task(taskId, newTitle,newContent)
            db.updateTask(updateTask)
            finish()
            Toast.makeText(this,"Changes Saved.",Toast.LENGTH_SHORT).show()
        }

        }
    }
