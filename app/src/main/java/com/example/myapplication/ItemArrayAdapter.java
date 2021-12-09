package com.example.myapplication;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

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
import java.util.ArrayList;


public class ItemArrayAdapter extends RecyclerView.Adapter<com.example.myapplication.ItemArrayAdapter.ViewHolder> {

        //All methods in this adapter are required for a bare minimum recyclerview adapter
        private int listItemLayout;
        private ArrayList<Recipe> itemList;
        // Constructor of the class
        public ItemArrayAdapter(int layoutId, ArrayList<Recipe> itemList) {
            listItemLayout = layoutId;
            this.itemList = itemList;
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
        public void onBindViewHolder(final com.example.myapplication.ItemArrayAdapter.ViewHolder holder, final int listPosition) {
            TextView item = holder.item;
            item.setText(itemList.get(listPosition).getName());
        }

        // Static inner class to initialize the views of rows
        static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView item;
            public ViewHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                item = (TextView) itemView.findViewById(R.id.recipe_name);
            }
            @Override
            public void onClick(View view) {
                Log.d("onclick", "onClick " + getLayoutPosition() + " " + item.getText());
            }
        }
    }

