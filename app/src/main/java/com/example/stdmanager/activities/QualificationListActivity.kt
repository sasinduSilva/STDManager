package com.example.stdmanager.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.stdmanager.databinding.ActivityQualificationListBinding
import com.example.stdmanager.model.UserQualificationModel
import com.example.stdmanager.util.CommonDatabaseHelper
import com.example.stdmanager.util.StateClass

class QualificationListActivity:AppCompatActivity() {

    private lateinit var binding: ActivityQualificationListBinding
    private lateinit var db: CommonDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQualificationListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = CommonDatabaseHelper(this)

        binding.btnAddQualification.setOnClickListener{

            var intent = Intent(this, AddQualificationActivity::class.java)
            startActivity(intent)
        }

        binding.btnSaveQualification.setOnClickListener{
//            println(StateClass.globalQualifications.contentToString())
            var result = false
            for (qualification in StateClass.globalQualifications ){
                val qualificationObj = UserQualificationModel(
                    0,
                    qualification.qualification,
                    qualification.instituteName,
                    qualification.startedDate,
                    qualification.endDate,
                    qualification.grade
                )
                result = db.insertQualification(qualificationObj)

            }

            if (result == true){
                Toast.makeText(this,"Qualifications Saved", Toast.LENGTH_SHORT).show()

            }
        }

    }
}