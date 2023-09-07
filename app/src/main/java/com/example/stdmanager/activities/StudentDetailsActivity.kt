package com.example.stdmanager.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.stdmanager.databinding.ActivityStudentDetailsBinding
import com.example.stdmanager.model.UserDetailModel
import com.example.stdmanager.util.UserDetailsDatabaseHelper

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentDetailsBinding
    private lateinit var db : UserDetailsDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = UserDetailsDatabaseHelper(this)

        binding.btnAddQualification.setOnClickListener{
            var intent = Intent(this, QualificationListActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnSaveDetails.setOnClickListener {
            val firstName = binding.txtInputFirstName.text.toString()
            val lastName = binding.txtInputLastName.text.toString()
            val contactNumber = binding.txtInputContactNumber.text.toString()
            val email = binding.txtInputEmail.text.toString()
            val parentName = binding.txtParentName.text.toString()
            val parentContactNumber = binding.txtParentContactNumber.text.toString()
            val parentEmail = binding.txtParentEmail.text.toString()

            val details = UserDetailModel(0,firstName,lastName,contactNumber,email,parentName,parentContactNumber,parentEmail)
            var result = db.insertDetails(details)


            if (result == true){
                Toast.makeText(this,"Details Saved", Toast.LENGTH_SHORT).show()
            }
        }







    }
}