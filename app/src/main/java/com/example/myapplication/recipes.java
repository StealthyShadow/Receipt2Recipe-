package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class recipes extends AppCompatActivity {

    private JSONArray JSONresponse;
    private List<Recipe> recipeList = new ArrayList<>();
    RequestQueue requestQueue;
    int responsecounter = 0;
    RecyclerView recipeDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipies);
        recipeDisplay = (RecyclerView) findViewById(R.id.recyclerView);
        Log.i("Info", "about to call createRecipes");
        Bundle bundle = getIntent().getExtras();
        String ingredients = bundle.getString("message");
        createRecipes(ingredients);

//        createRecipes("beef,carrots,onions");

    }

    public void createRecipes(String searchText) {
        String url = "https://api.spoonacular.com/recipes/findByIngredients?ingredients=" + searchText + "&number=30&instructionsRequired=true&apiKey=b3c36d18299c4c69824ac3a330ea596e";
        requestQueue = Volley.newRequestQueue(this);
        Log.i("Info", "about to call onResponse in createRecipes");
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Log.i("Info", "called onResponse");
                try {
                    Log.i("Info", String.valueOf(response == null));
                    JSONresponse = response;

                    Log.i("Info", String.valueOf(JSONresponse));
                    for (int i = 0; i < JSONresponse.length(); i++) {
                        JSONObject jsonObject1;
                        jsonObject1 = JSONresponse.getJSONObject(i);
                        recipeList.add(new Recipe(jsonObject1.optString("id"), jsonObject1.optString("title"), jsonObject1.optString("image")));
                        Log.i("Info", "recipe added");
                    }
                    responsecounter--;

                    if (responsecounter == 0) {
                        launchRecipes();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("the res is error:", error.toString());
                    }
                }
        );

        responsecounter++;
        requestQueue.add(jsonObjectRequest);
//        return recipeList;
    }

    private void launchRecipes() {

        ItemArrayAdapter adapter = new ItemArrayAdapter(R.layout.recipe_linear_layout, new ArrayList<Recipe>(recipeList));
        recipeDisplay = (RecyclerView) findViewById(R.id.recyclerView);
        recipeDisplay.setLayoutManager(new LinearLayoutManager(this));
        recipeDisplay.setItemAnimator(new DefaultItemAnimator());
        recipeDisplay.setAdapter(adapter);
//        if (recipeList == null) {
//            Log.i("Info", "recipes are null");
//        } else {
//            Log.i("Info", "entered for loop");
//            Log.i("Info", String.valueOf(recipeList.size()));
//            for (int i = 0; i < recipeList.size(); i++) {
//                Log.i("Info in loop", recipeList.get(i).getName());
//            }
//        }
    }
}