package com.example.stdmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stdmanager.databinding.ActivitySignupBinding
import com.example.stdmanager.databinding.ActivityStudentDetailsBinding

class SignUpActivity :AppCompatActivity(){

    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)







    }
}