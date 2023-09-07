package com.example.stdmanager.model

import java.sql.Date

data class UserQualificationModel(
    val id: Int,
    val qualification: String,
    val instituteName: String,
    val startedDate: String,
    val endDate: String,
    val grade: String
)
