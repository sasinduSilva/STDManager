package com.example.stdmanager

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stdmanager.databinding.ActivityQualificationListBinding
import com.example.stdmanager.databinding.ActivitySignupBinding

class QualificationListActivity:AppCompatActivity() {

    private lateinit var binding: ActivityQualificationListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQualificationListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddQualification.setOnClickListener{

            var intent = Intent(this,AddQualificationActivity::class.java)
            startActivity(intent)
        }

    }
}