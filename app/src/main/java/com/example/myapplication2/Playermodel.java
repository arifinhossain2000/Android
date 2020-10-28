package com.example.myapplication2;

import java.io.Serializable;

public class Playermodel implements Serializable {

    private  String id;
    private  String name;
    private  String type;
    private  String code;

    public Playermodel(String id, String name, String type, String code) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.code = code;
    }

    public String getId() {
        return id;
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
