package com.example.stdmanager.model

import java.sql.Date

data class UserQualificationModel(
    val id: Int,
    val instituteName: String,
    val startedDate: Date,
    val endDate: Date,
    val grade: String
)
