package com.example.myapplication2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities ={Playermodel.class},version = 1)

public abstract class PlayerData extends RoomDatabase {

    public abstract PlayerDao playerDao();


}
