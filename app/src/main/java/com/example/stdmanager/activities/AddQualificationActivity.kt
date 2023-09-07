package com.example.stdmanager.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.stdmanager.databinding.ActivityAddQualificationBinding
import com.example.stdmanager.databinding.ActivityQualificationListBinding
import com.example.stdmanager.model.UserQualificationModel
import com.example.stdmanager.util.StateClass
import java.sql.Date


class AddQualificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddQualificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddQualificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSaveQualification.setOnClickListener {
            val qualification = binding.txtInputQualification.text.toString()
            val instituteName = binding.txtInputInstituteName.text.toString()
            val startDate = binding.txtInputStartedDate.text.toString()
            val endDate = binding.txtInputEndDate.text.toString()
            val grade = binding.txtInputGrade.text.toString()
            val userQualificationModel = UserQualificationModel(
                0,
                qualification,
                instituteName,
                startDate,
                endDate,
                grade
            )

            StateClass.globalQualifications += userQualificationModel
            Toast.makeText(this,"Qualifications Processed Successfully", Toast.LENGTH_SHORT).show()
            finish()

        }

    }
}