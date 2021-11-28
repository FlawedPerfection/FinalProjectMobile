package com.example.finalproject;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

//Custom adapter class to display more than one item in a list view
public class NewsItemAdapter extends ArrayAdapter<NewsItem> {

    private Context mContext;
    int mResource;

    public NewsItemAdapter(@NonNull Context context, int resource, ArrayList<NewsItem> objects) {
        super(context, resource, objects);
        this.mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String title = getItem(position).getTitle();
        String pubDate = getItem(position).getPubDate();

        //Create the newsItem object with the information
        NewsItem newsItem = new NewsItem(title, pubDate);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView titleText = (TextView) convertView.findViewById(R.id.toptext);
        TextView pubDateText = (TextView) convertView.findViewById(R.id.bottomtext);

        titleText.setText(title);
        pubDateText.setText(pubDate);

        return convertView;
    }
}
