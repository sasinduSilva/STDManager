package com.example.stdmanager.model

data class UserDetailModel(
    val id: Int,
    val firstName: String,
    val lastName : String,
    val contactNumber: Number,
    val email: String,
    val parentName: String,
    val parentContactNumber: Number,
    val parentEmail: String
)
