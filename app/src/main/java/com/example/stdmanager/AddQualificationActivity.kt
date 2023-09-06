package com.example.stdmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stdmanager.databinding.ActivityAddQualificationBinding
import com.example.stdmanager.databinding.ActivityQualificationListBinding


class AddQualificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddQualificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddQualificationBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}