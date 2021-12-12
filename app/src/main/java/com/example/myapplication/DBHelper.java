package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBHelper {
    SQLiteDatabase sqLiteDatabase;

    public DBHelper(SQLiteDatabase sqLiteDatabase){
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public void createTable(){
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS ingredients" +
                "(id INTENGER PRIMARY KEY, username TEXT,name TEXT)");
    }
    public ArrayList<Ingredient> readIngredients(String username){
        createTable();
        Cursor c = sqLiteDatabase.rawQuery(String.format("SELECT * from ingredients where username like '%s'",username),null);


        int nameIndex = c.getColumnIndex("name");

        c.moveToFirst();

        ArrayList<Ingredient> ingredientsList = new ArrayList<>();

        while (!c.isAfterLast()){
            String name = c.getString(nameIndex);

            Ingredient ingredient = new Ingredient(name,username);
            ingredientsList.add(ingredient);
            c.moveToNext();
        }
        c.close();
        sqLiteDatabase.close();
        return ingredientsList;
    }
    public void saveIngredients(String name, String user){
        createTable();
        sqLiteDatabase.execSQL(String.format("INSERT INTO ingredients (name, username) VALUES ('%s','%s')",
                name,user));
    }
    public void updateIngredient(String name, String user){
        createTable();
        sqLiteDatabase.execSQL(String.format("UPDATE ingredients set name = '%s' and username = '%s'",name,user));
    }
    public void deleteIngredient(String ingred){
        createTable();
        sqLiteDatabase.execSQL(String.format("DELETE FROM ingredients WHERE name = '%s'",ingred));
    }

    public void clearIngredients() {
        createTable();
        sqLiteDatabase.execSQL(String.format("DELETE FROM ingredients"));
    }


}
