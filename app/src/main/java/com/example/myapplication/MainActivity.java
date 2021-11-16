package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void saveIngredient(View view){
        EditText input = (EditText) findViewById(R.id.ingredientEditText);
        Context context = getApplicationContext();
        sqLiteDatabase = context.openOrCreateDatabase("ingredients", Context.MODE_PRIVATE,null);
        DBHelper db = new DBHelper(sqLiteDatabase);
        db.saveIngredients(input.getText().toString(),"user");
        input.setText("");

        
    }

    public void viewIngredients(View view){
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
    static final int REQUEST_IMAGE_CAPTURE = 1;

    public void takePicture(View view){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivity(takePictureIntent);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }
}