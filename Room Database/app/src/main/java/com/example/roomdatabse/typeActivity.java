package com.example.roomdatabse;


mport androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Player.class},version = 1)
public abstract class PlayerDB extends RoomDatabase {

    public abstract PlayerDAO playerDAO();
}