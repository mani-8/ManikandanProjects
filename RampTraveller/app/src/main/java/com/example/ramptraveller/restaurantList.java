package com.example.ramptraveller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class restaurantList extends AppCompatActivity {

    ListView list;
    String restaurant[] = {
            "Murugan Idly Shop",
            "Thalappakatti",
            "SaravanaBhavan",
            "The Great Kabab Factory",
            "Sree Sabarees",
            "Place to Bee",
            "Tava",
            "Hotel Saravana",
            "Hotel TamilNadu",
            "Aksaka"

    };

    Integer[] imageId = {
            R.drawable.rest1,
            R.drawable.rest1,
            R.drawable.rest1,
            R.drawable.rest1,
            R.drawable.rest1,
            R.drawable.rest1,
            R.drawable.rest1,
            R.drawable.rest1,
            R.drawable.rest1,
            R.drawable.rest1,
            R.drawable.rest1,
            R.drawable.rest1,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        customRestList adapter = new customRestList(restaurantList.this, restaurant, imageId);

        list = findViewById(R.id.Restaurant_list);
        list.setAdapter(adapter);

    }
}