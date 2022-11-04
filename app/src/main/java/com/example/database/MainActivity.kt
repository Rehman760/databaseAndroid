package com.example.database

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    lateinit var db:SQLiteDatabase
    val DBNAME="STUDENTS"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //open database, if not created will create it
        db=openOrCreateDatabase(DBNAME, MODE_PRIVATE,null)

        //db.insert,update,delete,query
        //db.execSQL
        //db.rawQuery
        //creating table of users

        val tableQuery="CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY,username TEXT UNIQUE,password TEXT)"
        db.execSQL(tableQuery)
        //insert method needs 3 things (table,null columns,data), key:value

        insertData(1,"Rehman","123")
        insertData(2,"Ali","123")
        insertData(3,"Ameer","123")
        insertData(4,"shoaib","123")
        insertData(5,"Mubeen","123")
        getAllData("user")
    }
    fun insertData(id:Int,username:String,password:String){
        val cv=ContentValues()

        cv.put("id",id)
        cv.put("username",username)
        cv.put("password",password)
        db.insert("user",null,cv)
    }
    fun getAllData(table:String){
        val query="SELECT * FROM ${table}"
        val cursor:Cursor=db.rawQuery(query,null)
        var data=""
        if(cursor.count>0){
            cursor.moveToFirst()
            do {
                data+=cursor.getString(1)+"\n"
            }while (cursor.moveToNext())

        }
        val label: TextView = findViewById(R.id.dataView)
        label.text = data
    }
}