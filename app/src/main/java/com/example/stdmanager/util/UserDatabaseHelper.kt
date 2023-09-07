package com.example.stdmanager.util

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.stdmanager.model.UserModel

class UserDatabaseHelper(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "stdmanager.db"
        private const val DATABASE_VERSION = 2
        private const val TABLE_NAME = "UserTable"
        private const val COLUMN_ID = "id"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "password"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_EMAIL TEXT, $COLUMN_PASSWORD TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }
//insert user function
    fun insertUser(user: UserModel){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_EMAIL,user.email)
            put(COLUMN_PASSWORD,user.password)
        }
        db.insert(TABLE_NAME,null,values)
        db.close()

    }
// is user existing check function
    fun isUserExists(email:String):Boolean{
        val db = readableDatabase
        val query = "SELECT COUNT(*) FROM $TABLE_NAME WHERE $COLUMN_EMAIL = ?"
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
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?"
        val cursor = db.rawQuery(query, arrayOf(email,password))

        val signedSuccess = cursor.moveToFirst()
        cursor.close()
        db.close()

        return signedSuccess


    }
}