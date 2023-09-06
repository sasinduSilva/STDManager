package com.example.stdmanager

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.stdmanager.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

 private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener{
            var intent = Intent(this,StudentDetailsActivity::class.java)
            startActivity(intent)


        }

        binding.btnSignUp.setOnClickListener {
            var intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)

        }

    }
}