package com.example.myapplication;

public class Ingredient {
    private String name;
    private String username;

    public Ingredient(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public String getName() {
        return name;
    }
    public String getUsername(){
        return username;
    }
}
