package com.example.khatabook

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.khatabook.StatusModel

class Database(context:Context?) :SQLiteOpenHelper(context,"AwM.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE sad (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,mobile TEXT,bill TEXT)"
              db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertdata(n1: String,a1:String,s1:String){
        var db = writableDatabase

        var contentValues = ContentValues()
        contentValues.put("name",n1)
        contentValues.put("mobile",a1)
        contentValues.put("bill",s1)

        db.insert("sad",null,contentValues)

    }

    @SuppressLint("Range")
    fun readData(): ArrayList<StatusModel> {
        var datalist = arrayListOf<StatusModel>()
        var db = readableDatabase
        var query = "SELECT * FROM sad"
        val cursor = db.rawQuery(query,null)
        if (cursor.moveToFirst())
        {
            do
            {
                var id = cursor.getString(cursor.getColumnIndex("id")).toString()
                var name = cursor.getString(cursor.getColumnIndex("name")).toString()
                var mobile = cursor.getString(cursor.getColumnIndex("mobile")).toString()
                var bill = cursor.getString(cursor.getColumnIndex("bill")).toString()

                var d1 = StatusModel(id,name,mobile,bill)
                datalist.add(d1)
            } while (cursor.moveToNext())
        }
        return datalist;
    }




}
