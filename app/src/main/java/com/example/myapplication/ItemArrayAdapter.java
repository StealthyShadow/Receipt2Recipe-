package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.ItemArrayAdapter;
import com.example.myapplication.R;
import com.example.myapplication.Recipe;

import java.util.ArrayList;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
//import com.codexpedia.list.viewholder.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ItemArrayAdapter extends RecyclerView.Adapter<com.example.myapplication.ItemArrayAdapter.ViewHolder> {

        //All methods in this adapter are required for a bare minimum recyclerview adapter
        private int listItemLayout;
        private ArrayList<Recipe> itemList;
        private Context context;
        // Constructor of the class
        public ItemArrayAdapter(int layoutId, ArrayList<Recipe> itemList, Context context) {
            listItemLayout = layoutId;
            this.itemList = itemList;
            this.context = context;
        }

        // get the size of the list
        @Override
        public int getItemCount() {
            return itemList == null ? 0 : itemList.size();
        }


        // specify the row layout file and click for each row
        @Override
        public com.example.myapplication.ItemArrayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
            com.example.myapplication.ItemArrayAdapter.ViewHolder myViewHolder = new com.example.myapplication.ItemArrayAdapter.ViewHolder(view);
            return myViewHolder;
        }

        // load data in each row element
        @Override
        public void onBindViewHolder(final com.example.myapplication.ItemArrayAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int listPosition) {
            TextView item = holder.item;
            item.setText(itemList.get(listPosition).getName());
            holder.itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getRecipeLinks(itemList.get(listPosition).getid(), listPosition);
//                    Log.i("info", itemList.get(listPosition).getName());
                }
            });

        }


        RequestQueue requestQueueLink;
        JSONObject JSONresponse;
        int responsecounterLink = 0;

        private void getRecipeLinks(String ID, int position){
            if(ID == null){
                Log.i("ERROR", "Recipe ID is NULL");
            }else {
                String url = "https://api.spoonacular.com/recipes/" + ID + "/information?apiKey=b3c36d18299c4c69824ac3a330ea596e";
//                Log.i("Info1", url);
//                Log.i("Info1: Context", String.valueOf(context));
                requestQueueLink = Volley.newRequestQueue(context);
//                Log.i("Info1", "about to call onResponse in getRecipeLinks");
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
//                        Log.i("Info1", "called onResponse");
                        try {
//                            Log.i("Info1", String.valueOf(response == null));
                            JSONresponse = response;
//                            Log.i("Info1", String.valueOf(JSONresponse));
                            JSONObject jsonObject1 = new JSONObject(response.toString());
                            itemList.get(position).setLink(jsonObject1.optString("spoonacularSourceUrl"));

                            responsecounterLink--;

                            if (responsecounterLink == 0) {
                                launchRecipeLink(itemList.get(position).getLink());
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                    new Response.ErrorListener() {
                        @SuppressLint("LongLogTag")
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("the res is error in second call:", error.toString());
                        }
                    }
                );

                responsecounterLink++;
                requestQueueLink.add(jsonObjectRequest);
            }
    }

    public void launchRecipeLink(String link){
        Log.i("Info at link launch", link);
        Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(link));
        context.startActivity(intent);
    }

        // Static inner class to initialize the views of rows
        static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView item;
            View itemview;
            public ViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                item = (TextView) itemView.findViewById(R.id.recipe_name);
                itemview = itemView;
            }
            @Override
            public void onClick(View view) {

            }
        }
    }

