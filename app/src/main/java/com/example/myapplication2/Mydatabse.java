package com.example.myapplication2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Mydatabse extends SQLiteOpenHelper{

    public static final String databaseName = "Note";
    public static final String tableName = "playerInfo";
    public static final String playerCode = "Code";
    public static final String playerName = "Name";
    public static final String playerId = "_Id";
    public static final String playerType ="Type";
    public static final int versionNo =4;
    private Context context;
    public static final String createTable = "CREATE TABLE "+tableName+"("+playerId+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            ""+ ""+playerName+" VARCHAR(100), "+playerType+" VARCHAR(100), "+playerCode+" INTEGER);";


    public static final String upgradeTable = " DROP TABLE IF EXISTS " + tableName;

    public static final String displayTable = " SELECT * FROM " + tableName;


    public Mydatabse( Context context) {
        super(context, databaseName, null, versionNo);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(createTable);
            Toast.makeText(context, " Table created", Toast.LENGTH_SHORT).show();

        }
        catch (Exception E){
            Toast.makeText(context, ""+E, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            Toast.makeText(context, " Table upgraded ", Toast.LENGTH_SHORT).show();
            db.execSQL(upgradeTable);
            onCreate(db);
        }
        catch (Exception E){

            Toast.makeText(context, ""+E, Toast.LENGTH_SHORT).show();
        }
    }

    public long insert(String name, String type, String code){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(playerName,name);
        contentValues.put(playerType, type);
        contentValues.put(playerCode, code);
         long values=sqLiteDatabase.insert(tableName,null,contentValues);

         return values;
    }

    public Cursor show(){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(displayTable,null);

        return cursor;

    }



    public int delete(String id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int value = sqLiteDatabase.delete(tableName,playerId+" = ? ",new String[]{id});

        return value;
    }

    public  long update(String id, String name, String type, String code){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(playerId,id);
        contentValues.put(playerName,name);
        contentValues.put(playerType, type);
        contentValues.put(playerCode, code);

        long value = sqLiteDatabase.update(tableName,contentValues,playerId+" =?",new String[]{id});

        return  value;



    }


}
