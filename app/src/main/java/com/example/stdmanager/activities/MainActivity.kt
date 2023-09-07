package com.example.stdmanager.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.example.stdmanager.databinding.ActivityMainBinding
import com.example.stdmanager.util.CommonDatabaseHelper


class MainActivity : AppCompatActivity() {

 private lateinit var binding: ActivityMainBinding
 private lateinit var db:CommonDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = CommonDatabaseHelper(this)
        binding.btnLogin.setOnClickListener{
            var intent = Intent(this, StudentDetailsActivity::class.java)

            val email = binding.txtEmailAddress.text.toString()
            val password = binding.txtPassword.text.toString()
            val singedInResult = db.signInUser(email,password)
            if(singedInResult == true){
                Toast.makeText(this,"Signed in successfully", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"Signed in failled", Toast.LENGTH_SHORT).show()
            }



        }

        binding.btnSignUp.setOnClickListener {
            var intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()

        }

    }
}