package com.example.myapplication2;

public class Model {
    private int position;
    private String name;
    private String type;



    public Model(int position, String name, String type) {
        this.position = position;
        this.name = name;
        this.type = type;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
