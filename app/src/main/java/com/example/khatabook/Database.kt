package com.example.khatabook

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.khatabook.model.StatusModel
import com.example.khatabook.model.StatusModel2


class Database(context:Context?) :SQLiteOpenHelper(context,"AwM.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,mobile TEXT)"
        val query2 = "CREATE TABLE transactions (id INTEGER PRIMARY KEY AUTOINCREMENT,userId TEXT,product TEXT,date TEXT,money TEXT,status INTEGER,time String)"
        db?.execSQL(query)
        db?.execSQL(query2)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS '" + "user" + "'")
        db?.execSQL("DROP TABLE IF EXISTS '" + "transactions" + "'")
        onCreate(db)
    }

    fun addCustomer(name: String,mobile:String){
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name",name)
        contentValues.put("mobile",mobile)

        db.insert("user",null,contentValues)
    }

    fun insertTransactions(userId:String, product: String, amount: String, date: String,status:Int,time:String){
            val db = writableDatabase
            val contentValues = ContentValues()
            contentValues.put("userId",userId)
            contentValues.put("product",product)
            contentValues.put("date",date)
            contentValues.put("money",amount)
            contentValues.put("status",status)
            contentValues.put("time",time)
            db.insert("transactions",null,contentValues)
        }
    fun updatename(id:String,name: String,mobile:String){
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name",name)
        contentValues.put("mobile",mobile)

        db.update("user",contentValues,"id ='$id'",null)
    }
    fun Deletename(id:String,name: String,mobile:String){
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name",name)
        contentValues.put("mobile",mobile)



        db.delete("user", "id=?", arrayOf(id))
    }

        fun updatedata(product: String, amount: String, date: String, time:String, id: String,userId: String){
            val db = writableDatabase
            val contentValues = ContentValues()
        contentValues.put("product",product)
        contentValues.put("money",amount)
        contentValues.put("date",date)
        contentValues.put("time",time)

        db.update("transactions", contentValues, "id ='$id' AND userId ='$userId'", null)


    }
    fun DeleteData(product: String, amount: String, date: String, time: String,id: String,userId: String){
        val db = writableDatabase
        val contentValues = ContentValues()
        contentValues.put("product",product)
        contentValues.put("money",amount)
        contentValues.put("date",date)
        contentValues.put("time",time)

        db.delete("transactions", "id=? AND userId=?", arrayOf(id, userId))

        db.close()

    }

    @SuppressLint("Range")
    fun readData(): ArrayList<StatusModel> {
        val datalist = arrayListOf<StatusModel>()
        val db = readableDatabase
        val query = "SELECT * FROM user"
        val cursor = db.rawQuery(query,null)
        if (cursor.moveToFirst())
        {
            do
            {
                val id = cursor.getString(cursor.getColumnIndex("id")).toString()
                val name = cursor.getString(cursor.getColumnIndex("name")).toString()
                val mobile = cursor.getString(cursor.getColumnIndex("mobile")).toString()


                val d1 = StatusModel(id,name,mobile)
                datalist.add(d1)
            } while (cursor.moveToNext())
        }
        return datalist
    }

    @SuppressLint("Range")
    fun readTransactions(userId: String): ArrayList<StatusModel2> {
        val datalist = arrayListOf<StatusModel2>()
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM transactions WHERE userId = '$userId'", null)
        if (cursor.moveToFirst())
        {
            do
            {
                val id = cursor.getString(cursor.getColumnIndex("id")).toString()
                val userId = cursor.getString(cursor.getColumnIndex("userId")).toString()
                val date = cursor.getString(cursor.getColumnIndex("date")).toString()
                val product = cursor.getString(cursor.getColumnIndex("product")).toString()
                val money = cursor.getString(cursor.getColumnIndex("money")).toString()
                val status = cursor.getString(cursor.getColumnIndex("status")).toString()
                val time = cursor.getString((cursor.getColumnIndex("time")))
                val d1 = StatusModel2(id,userId,product,money,date,status,time)
                datalist.add(d1)
            } while (cursor.moveToNext())
        }
        return datalist
    }
    @SuppressLint("Range", "Recycle")
    fun readFilter(dat: String): ArrayList<StatusModel2> {
        val datalist = arrayListOf<StatusModel2>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM transactions WHERE date ='$dat' AND status = '0'", null)
        if (cursor.moveToFirst())
        {
            do
            {
                val id = cursor.getString(cursor.getColumnIndex("id")).toString()
                val userId = cursor.getString(cursor.getColumnIndex("userId")).toString()
                val date = cursor.getString(cursor.getColumnIndex("date")).toString()
                val product = cursor.getString(cursor.getColumnIndex("product")).toString()
                val money = cursor.getString(cursor.getColumnIndex("money")).toString()
                val status = cursor.getString(cursor.getColumnIndex("status")).toString()
                val time = cursor.getString((cursor.getColumnIndex("time")))

                val d1 = StatusModel2(id, userId, product, money, date, status,time)
                datalist.add(d1)
            } while (cursor.moveToNext())
        }
        return datalist
    }
    @SuppressLint("Range")
    fun History(): ArrayList<StatusModel2> {
        val datalist = arrayListOf<StatusModel2>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM transactions WHERE status = '0'OR status ='1' ", null)
        if (cursor.moveToFirst())
        {
            do
            {
                val id = cursor.getString(cursor.getColumnIndex("id")).toString()
                val userId = cursor.getString(cursor.getColumnIndex("userId")).toString()
                val date = cursor.getString(cursor.getColumnIndex("date")).toString()
                val product = cursor.getString(cursor.getColumnIndex("product")).toString()
                val money = cursor.getString(cursor.getColumnIndex("money")).toString()
                val status = cursor.getString(cursor.getColumnIndex("status")).toString()
                val time = cursor.getString((cursor.getColumnIndex("time")))

                val d1 = StatusModel2(id, userId, product, money, date, status,time)
                datalist.add(d1)
            } while (cursor.moveToNext())
        }
        return datalist
    }

    @SuppressLint("Range")
    fun readTotal(): ArrayList<StatusModel2> {
        val datalist = arrayListOf<StatusModel2>()
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM transactions", null)
        if (cursor.moveToFirst())
        {
            do
            {
                val id = cursor.getString(cursor.getColumnIndex("id")).toString()
                val userId = cursor.getString(cursor.getColumnIndex("userId")).toString()
                val date = cursor.getString(cursor.getColumnIndex("date")).toString()
                val product = cursor.getString(cursor.getColumnIndex("product")).toString()
                val money = cursor.getString(cursor.getColumnIndex("money")).toString()
                val status = cursor.getString(cursor.getColumnIndex("status")).toString()
                val time = cursor.getString((cursor.getColumnIndex("time")))
                val d1 = StatusModel2(id,userId,product,money,date,status,time)
                datalist.add(d1)
            } while (cursor.moveToNext())
        }
        return datalist
    }


}


