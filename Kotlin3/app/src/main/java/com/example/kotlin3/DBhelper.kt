package com.example.kotlin3

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.lang.Exception

class DBhelper(private  val context:Context):SQLiteOpenHelper(context, DB_Name,null, Version) {

    companion object{

        const val DB_Name = "myDatabase"
        const val TableName = "userTable"
        const val Name = "userName"
        const val Email = "userEmail"
        const val Id = "userId"
        const val Version = 1

    }

    override fun onCreate(db: SQLiteDatabase?) {

        val create_table= "CREATE TABLE "+ TableName +" " +
                "( "+ Id +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ Name +" VARCHAR(100), "+ Email+" VARCHAR(100) ) ;"

        try {
            db?.execSQL(create_table)
            Toast.makeText(context,"Table Created",Toast.LENGTH_LONG).show()

        }catch (e:Exception){

            Toast.makeText(context,""+e,Toast.LENGTH_LONG).show()

        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        val upgrade= "Drop TABLE IF EXISTS $TableName"

        val create_table= "CREATE TABLE "+ TableName +" " +
                "( "+ Id +" INTEGER PRIMARY KEY AUTOINCREMENT, "+ Name +" VARCHAR(100), "+ Email+" VARCHAR(100) ) ;"

        try {
            db?.execSQL(upgrade)
            Toast.makeText(context,"Table Upgraded",Toast.LENGTH_LONG).show()

        }catch (e:Exception){

            Toast.makeText(context,""+e,Toast.LENGTH_LONG).show()

        }

    }

    //insert in database

    fun insert(name:String,email:String):Long{

        val db = this.writableDatabase
        val contentValues= ContentValues()
        contentValues.put(Name,name)
        contentValues.put(Email,email)
        return db.insert(TableName,null,contentValues)
    }

    //retiveData

    fun show():Cursor{

        val db = this.readableDatabase
        val getData= "SELECT * FROM $TableName"
        return db.rawQuery(getData,null)
    }

    //Delete

    fun delete(id:String):Int{
        val db= this.writableDatabase
        return db.delete(TableName,"$Id= ?", arrayOf(id))
    }

    //update

    fun update(user:User):Int{
        val db = this.writableDatabase
        val contentValues= ContentValues()

        contentValues.put(Name, user.name)
        contentValues.put(Email, user.mail)
        return  db.update(TableName,contentValues, "$Id=?",arrayOf(user.id))
    }


    


}