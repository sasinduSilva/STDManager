package com.example.stdmanager.util

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.stdmanager.model.UserQualificationModel

class UserQualificationDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_NAME = "stdmanager.db"
        private const val DATABASE_VERSION = 3
        private const val TABLE_NAME = "qualificatiostable"
        private const val COLUMN_ID = "id"
        private const val COLUMN_QUALIFICATION = "qualification"
        private const val COLUMN_INSTITUTENAME = "instituteName"
        private const val COLUMN_STARTDATE = "startDate"
        private const val COLUMN_ENDDATE = "enddate"
        private const val COLUMN_GRADE = "grade"

    }

    override fun onCreate(db: SQLiteDatabase?) {

        val createTableQuery = "CREATE TABLE ${TABLE_NAME} (${COLUMN_ID} INTEGER PRIMARY KEY, ${COLUMN_QUALIFICATION} TEXT, ${COLUMN_INSTITUTENAME} TEXT,${COLUMN_STARTDATE} TEXT, ${COLUMN_ENDDATE} TEXT, ${COLUMN_GRADE} TEXT)"
         db?.execSQL(createTableQuery)

    }

    override fun onUpgrade(db: SQLiteDatabase?, newVersion: Int, oldVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS ${TABLE_NAME}"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertQualification(qualification:UserQualificationModel):Boolean{
        val db =writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_QUALIFICATION,qualification.qualification)
            put(COLUMN_INSTITUTENAME,qualification.instituteName)
            put(COLUMN_STARTDATE,qualification.startedDate)
            put(COLUMN_ENDDATE,qualification.endDate)
            put(COLUMN_GRADE,qualification.grade)
        }

        var result = db.insert(TABLE_NAME,null,values)
        db.close()

        return result != -1L

    }

    fun getAllQualifications():List<UserQualificationModel>{
        val qualificationsList = mutableListOf<UserQualificationModel>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query,null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val qualification = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QUALIFICATION))
            val institute = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_INSTITUTENAME))
            val startDate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STARTDATE))
            val endDate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ENDDATE))
            val grade = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_GRADE))

            val userQualification = UserQualificationModel(id,qualification,institute,startDate,endDate,grade)
            qualificationsList.add(userQualification)
        }

        cursor.close()
        db.close()

        return qualificationsList
    }


}