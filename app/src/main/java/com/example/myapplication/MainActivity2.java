package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    public static ArrayList<Ingredient> ingredients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();

        Context context = getApplicationContext();
        sqLiteDatabase = context.openOrCreateDatabase("ingredients", Context.MODE_PRIVATE,null);
        DBHelper db = new DBHelper(sqLiteDatabase);
        ingredients = db.readIngredients("user");

        ArrayList<String> displayIngredients = new ArrayList<>();
        for (Ingredient ingredient: ingredients){
            displayIngredients.add(String.format("%s", ingredient.getName()));
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,displayIngredients);
        ListView listView = (ListView) findViewById(R.id.ingredientList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Context context = getApplicationContext();
                sqLiteDatabase = context.openOrCreateDatabase("ingredients", Context.MODE_PRIVATE,null);
                DBHelper db = new DBHelper(sqLiteDatabase);
                Object toRemove = adapter.getItem(position);
                String str = toRemove.toString();
                adapter.remove(toRemove);
                Log.i("INFO", "onItemClick:" + str);
                db.deleteIngredient(str);
                sqLiteDatabase.close();
            }
        });
    }
    public void addMoreIngredients(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);


    }
}