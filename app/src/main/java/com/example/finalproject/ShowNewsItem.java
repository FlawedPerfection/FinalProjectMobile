package com.example.finalproject;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowNewsItem extends AppCompatActivity {

    RelativeLayout relativeLayout;
    //the static title display
    TextView titleText;
    TextView title;
    //the static description display
    TextView descriptionText;
    TextView description;
    //the static title display
    TextView pubDateText;
    TextView pubDate;
    //the static link display
    TextView linkText;
    TextView link;
    String titleValue;
    String descriptionValue;
    String linkValue;
    String pubDateValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news_item);


    }


}
