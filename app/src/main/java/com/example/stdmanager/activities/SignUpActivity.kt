package com.example.stdmanager.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.stdmanager.databinding.ActivitySignupBinding
import com.example.stdmanager.databinding.ActivityStudentDetailsBinding
import com.example.stdmanager.model.UserModel
import com.example.stdmanager.util.UserDatabaseHelper

class SignUpActivity :AppCompatActivity(){

    private lateinit var binding: ActivitySignupBinding
    private lateinit var db: UserDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = UserDatabaseHelper(this)

        binding.btnSignUp.setOnClickListener {
            val email = binding.txtInputEmail.text.toString()
            val password = binding.txtInputPassword.text.toString()
            val confirmPassword = binding.txtInputConfirmPassword.text.toString()
            if (password == confirmPassword){
                val user = UserModel(0,email,password)
                db.insertUser(user)
                finish()
                Toast.makeText(this,"Signed Up Successfully", Toast.LENGTH_SHORT).show()
            }
        }












    }
}