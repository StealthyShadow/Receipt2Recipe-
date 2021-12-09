package com.example.myapplication;

import java.util.List;

public class Recipe {
    private String id;
    private String title;
    private String image;
    private int missedIngredientCount;
    private List<String> missedIngredients;

    public Recipe(String id, String title, String image){
        this.id = id;
        this.title = title;
        this.image = image;
//        this.missedIngredientCount = missedIngredientCount;
//        this.missedIngredients = missedIngredients;
    }

    public String getid(){
        return this.id;
    }

    public String getName(){
        return this.title;
    }
    public String getImage(){
        return this.image;
    }

//    public int getMissedIngredientCount(){
//        return this.missedIngredientCount;
//    }
//
//    public List<String> getMissedIngredients(){
//        return this.missedIngredients;
//    }


}
