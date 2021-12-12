package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    private String m_Text = "";
    SQLiteDatabase sqLiteDatabase;
    public static ArrayList<Ingredient> ingredients = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //getSupportActionBar().hide();

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
                PopupMenu menu = new PopupMenu(MainActivity2.this,view);
                menu.getMenuInflater().inflate(R.menu.ingredient_menu,menu.getMenu());
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                       if(menuItem.getTitle().equals("Delete Ingredient")){
                           Context context = getApplicationContext();
                           sqLiteDatabase = context.openOrCreateDatabase("ingredients", Context.MODE_PRIVATE,null);
                           DBHelper db = new DBHelper(sqLiteDatabase);
                           Object toRemove = adapter.getItem(position);
                           String str = toRemove.toString();
                           adapter.remove(toRemove);
                           //Log.i("INFO", "onItemClick:" + str);
                           db.deleteIngredient(str);
                           sqLiteDatabase.close();
                           return true;
                       }
                       else{
                           AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                           builder.setTitle("Edit Ingredient");
                           final EditText input = new EditText(MainActivity2.this);
                           input.setHint("Type Here");
                           builder.setView(input);

                           builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   m_Text = input.getText().toString();
                                   ingredients.set(position,new Ingredient(m_Text,"user"));
                                   Context context = getApplicationContext();
                                   sqLiteDatabase = context.openOrCreateDatabase("ingredients", Context.MODE_PRIVATE,null);
                                   DBHelper db = new DBHelper(sqLiteDatabase);
                                   Object toRemove = adapter.getItem(position);
                                   String str = toRemove.toString();
                                   adapter.remove(toRemove);
                                   db.deleteIngredient(str);
                                   db.saveIngredients(m_Text,"user");
                                   adapter.add(m_Text);
                                   sqLiteDatabase.close();
                               }
                           });
                           builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   dialog.cancel();
                               }
                           });
                           builder.show();
                           return true;
                       }
                    }
                });
                menu.show();
            }
        });

        ArrayList<String> emptyList = new ArrayList<>();
        ArrayAdapter emptyAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, emptyList);

        Button removeAll = findViewById(R.id.wipeButton);

        removeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                sqLiteDatabase = context.openOrCreateDatabase("ingredients", Context.MODE_PRIVATE,null);
                DBHelper db = new DBHelper(sqLiteDatabase);
                listView.setAdapter(emptyAdapter);
                db.clearIngredients();
                ingredients = db.readIngredients("user");
                sqLiteDatabase.close();
            }
        });

        Button findRecipes = (Button) findViewById(R.id.FindRecipes);
        findRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("ingredients length", String.valueOf(ingredients.size()));
                if (ingredients.size() == 0) {
                    Toast toast = Toast.makeText(view.getContext(), "No ingredients available", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    addRecipes(view);
                }
            }
        });

    }
    public void addMoreIngredients(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);


    }

    public void addRecipes(View view){
        Intent intent = new Intent(this, recipes.class);

        String ingredientString = createStringFromList(ingredients);
        intent.putExtra("message", ingredientString);

        startActivity(intent);

    }

    public String createStringFromList(ArrayList<Ingredient> ingredients){
        String strIngredients = "";
        Log.i("Info", "ingredients are: " + String.valueOf(ingredients));
        for(int i = 0; i < ingredients.size(); i++){
            Log.i("Info", strIngredients + String.valueOf(i));
            strIngredients += ingredients.get(i).getName();
            if(i != ingredients.size()-1){
                strIngredients += ",";
            }

        }
        return strIngredients;
    }



}