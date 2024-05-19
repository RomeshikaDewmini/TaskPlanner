package com.example.taskplanner

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.taskplanner.databinding.ActivityMainBinding
import com.example.taskplanner.databinding.ActivitySplashBinding


class activity_splash : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)

        }
    }
