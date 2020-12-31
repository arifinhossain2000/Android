package com.example.myapplication2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlayerDao {

    @Insert
    public long insertData(Playermodel playermodel);

    @Query("select * from Player_table")
    public List<Playermodel> readData();


}
