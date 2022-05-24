package com.example.ramptraveller;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class customRestList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] restaurant;
    private final Integer[] imageId;

    public customRestList(Activity context, String[] restaurant, Integer[] imageId) {
        super(context, R.layout.activity_custom_rest_list, restaurant);
        this.context = context;
        this.restaurant = restaurant;
        this.imageId = imageId;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.activity_custom_rest_list, null, true);
        TextView txtTitle = rowView.findViewById(R.id.txt);
        ImageView imageView = rowView.findViewById(R.id.img);

        txtTitle.setText(restaurant[position]); //hotel[0]
        imageView.setImageResource(imageId[position]); //imageId[0]

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailRestaurant.class);
                intent.putExtra("key", restaurant[position]);
                context.startActivity(intent);
            }
        });

        return rowView;

    }

}
