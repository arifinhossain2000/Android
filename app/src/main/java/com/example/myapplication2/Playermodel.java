package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Player_table")

public class Playermodel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private  int id;

    private  String name;
    private  String type;
    private  String code;

    public Playermodel(  String name, String type, String code) {

        this.name = name;
        this.type = type;
        this.code = code;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCode() {
        return code;
    }
}
