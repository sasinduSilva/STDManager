package com.example.stdmanager.util

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.stdmanager.model.UserDetailModel
import com.example.stdmanager.model.UserModel
import com.example.stdmanager.model.UserQualificationModel

class CommonDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "stdmanager.db"
        private const val DATABASE_VERSION = 1
        //table 1
        private const val TABLE_NAME_1 = "UserTable"
        private const val COLUMN_ID_1 = "id"
        private const val COLUMN_EMAIL_1 = "email"
        private const val COLUMN_PASSWORD_1 = "password"
        //table 2
        private const val TABLE_NAME_2 = "userDetails"
        private const val COLUMN_ID_2 = "id"
        private const val COLUMN_FIRSTNAME_2 = "firstName"
        private const val COLUMN_LASTNAME_2 = "lastName"
        private const val COLUMN_CONTACTNUMBER_2 = "contactNumber"
        private const val COLUMN_EMAIL_2 = "email"
        private const val COLUMN_PARENTNAME_2 = "parentName"
        private const val COLUMN_PARENTCONTACTNUMBER_2 = "parentContactNumber"
        private const val COLUMN_PARENTEMAIL_2 = "parentEmail"

        //table 3
        private const val TABLE_NAME_3 = "qualificatiostable"
        private const val COLUMN_ID_3 = "id"
        private const val COLUMN_QUALIFICATION_3 = "qualification"
        private const val COLUMN_INSTITUTENAME_3 = "instituteName"
        private const val COLUMN_STARTDATE_3 = "startDate"
        private const val COLUMN_ENDDATE_3 = "enddate"
        private const val COLUMN_GRADE_3 = "grade"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        //create table 1
        val createTableQuery_1 = "CREATE TABLE ${TABLE_NAME_1} (${COLUMN_ID_1} INTEGER PRIMARY KEY, ${COLUMN_EMAIL_1} TEXT, ${COLUMN_PASSWORD_1} TEXT)"
        db?.execSQL(createTableQuery_1)

        //create table 2
        val createTableQuery_2 = "CREATE TABLE ${TABLE_NAME_2} (${COLUMN_ID_2} INTEGER PRIMARY KEY, ${COLUMN_FIRSTNAME_2} TEXT, ${COLUMN_LASTNAME_2} TEXT,${COLUMN_CONTACTNUMBER_2} TEXT, ${COLUMN_EMAIL_2} TEXT, ${COLUMN_PARENTNAME_2} TEXT,${COLUMN_PARENTCONTACTNUMBER_2} TEXT,${COLUMN_PARENTEMAIL_2} TEXT)"
        db?.execSQL(createTableQuery_2)

        //crete table 3
        val createTableQuery_3 = "CREATE TABLE ${TABLE_NAME_3} (${COLUMN_ID_3} INTEGER PRIMARY KEY, ${COLUMN_QUALIFICATION_3} TEXT, ${COLUMN_INSTITUTENAME_3} TEXT,${COLUMN_STARTDATE_3} TEXT, ${COLUMN_ENDDATE_3} TEXT, ${COLUMN_GRADE_3} TEXT)"
        db?.execSQL(createTableQuery_3)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //table 1
        val dropTableQuery = "DROP TABLE IF EXISTS ${TABLE_NAME_1}"
        db?.execSQL(dropTableQuery)
        onCreate(db)

        //table2
        val dropTableQuery_2 = "DROP TABLE IF EXISTS ${TABLE_NAME_2}"
        db?.execSQL(dropTableQuery_2)
        onCreate(db)

        //table 3
        val dropTableQuery_3 = "DROP TABLE IF EXISTS ${TABLE_NAME_3}"
        db?.execSQL(dropTableQuery_3)
        onCreate(db)
    }

    //////////////////////////table 1 methods
    fun insertUser(user: UserModel){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_EMAIL_1,user.email)
            put(COLUMN_PASSWORD_1,user.password)
        }
        db.insert(TABLE_NAME_1,null,values)
        db.close()

    }
    // is user existing check function
    fun isUserExists(email:String):Boolean{
        val db = readableDatabase
        val query = "SELECT COUNT(*) FROM ${TABLE_NAME_1} WHERE ${COLUMN_EMAIL_1} = ?"
        val cursor = db.rawQuery(query, arrayOf(email))

        var count = 0

        try {
            if(cursor.moveToFirst()){
                count = cursor.getInt(0)
            }
        }finally {
            cursor.close()
            db.close()
        }

        return count > 0
    }
    //    Sign in function
    fun signInUser(email: String,password:String):Boolean{
        val db = readableDatabase
        val query = "SELECT * FROM ${TABLE_NAME_1} WHERE ${COLUMN_EMAIL_1} = ? AND ${COLUMN_PASSWORD_1} = ?"
        val cursor = db.rawQuery(query, arrayOf(email,password))

        val signedSuccess = cursor.moveToFirst()
        cursor.close()
        db.close()

        return signedSuccess


    }
    /////////////////////////table 1 methods

    ////////////////////////////////////////////////////////////////Table 2 methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    fun insertDetails(details: UserDetailModel):Boolean{
        val db =writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_FIRSTNAME_2,details.firstName)
            put(COLUMN_LASTNAME_2,details.lastName)
            put(COLUMN_CONTACTNUMBER_2,details.contactNumber)
            put(COLUMN_EMAIL_2,details.email)
            put(COLUMN_PARENTNAME_2,details.parentName)
            put(COLUMN_PARENTCONTACTNUMBER_2,details.parentContactNumber)
            put(COLUMN_PARENTEMAIL_2,details.parentEmail)
        }

        var result = db.insert(TABLE_NAME_2,null,values)
        db.close()

        return result != -1L


    }

    ////////////////////////////////////////////////////////////////Table 2 methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    ////////////////////////////////////////////////////////////////Table 3 Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    fun insertQualification(qualification: UserQualificationModel):Boolean{
        val db =writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_QUALIFICATION_3,qualification.qualification)
            put(COLUMN_INSTITUTENAME_3,qualification.instituteName)
            put(COLUMN_STARTDATE_3,qualification.startedDate)
            put(COLUMN_ENDDATE_3,qualification.endDate)
            put(COLUMN_GRADE_3,qualification.grade)
        }

        var result = db.insert(TABLE_NAME_3,null,values)
        db.close()

        return result != -1L

    }

    fun getAllQualifications():List<UserQualificationModel>{
        val qualificationsList = mutableListOf<UserQualificationModel>()
        val db = readableDatabase
        val query = "SELECT * FROM ${TABLE_NAME_3}"
        val cursor = db.rawQuery(query,null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_3))
            val qualification = cursor.getString(cursor.getColumnIndexOrThrow(
                COLUMN_QUALIFICATION_3
            ))
            val institute = cursor.getString(cursor.getColumnIndexOrThrow(
                COLUMN_INSTITUTENAME_3
            ))
            val startDate = cursor.getString(cursor.getColumnIndexOrThrow(
                COLUMN_STARTDATE_3
            ))
            val endDate = cursor.getString(cursor.getColumnIndexOrThrow(
                COLUMN_ENDDATE_3
            ))
            val grade = cursor.getString(cursor.getColumnIndexOrThrow(
                COLUMN_GRADE_3
            ))

            val userQualification = UserQualificationModel(id,qualification,institute,startDate,endDate,grade)
            qualificationsList.add(userQualification)
        }

        cursor.close()
        db.close()

        return qualificationsList
    }
    ////////////////////////////////////////////////////////////////Table 3 Methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
}