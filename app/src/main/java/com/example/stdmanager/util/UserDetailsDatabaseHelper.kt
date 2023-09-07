package com.example.stdmanager.util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.stdmanager.model.UserDetailModel

class UserDetailsDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "stdmanager.db"
        private const val DATABASE_VERSION = 3
        private const val TABLE_NAME = "userDetails"
        private const val COLUMN_ID = "id"
        private const val COLUMN_FIRSTNAME = "firstName"
        private const val COLUMN_LASTNAME = "lastName"
        private const val COLUMN_CONTACTNUMBER = "contactNumber"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PARENTNAME = "parentName"
        private const val COLUMN_PARENTCONTACTNUMBER = "parentContactNumber"
        private const val COLUMN_PARENTEMAIL = "parentEmail"




    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE ${TABLE_NAME} (${COLUMN_ID} INTEGER PRIMARY KEY, ${COLUMN_FIRSTNAME} TEXT, ${COLUMN_LASTNAME} TEXT,${COLUMN_CONTACTNUMBER} TEXT, ${COLUMN_EMAIL} TEXT, ${COLUMN_PARENTNAME} TEXT,${COLUMN_PARENTCONTACTNUMBER} TEXT,${COLUMN_PARENTEMAIL} TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS ${TABLE_NAME}"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertDetails(details:UserDetailModel){
        val db = writableDatabase


    }

}