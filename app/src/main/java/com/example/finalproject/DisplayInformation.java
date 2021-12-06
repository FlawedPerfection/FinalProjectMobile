package com.example.finalproject;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayInformation extends RssActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news_item);

        Bundle extras = getIntent().getExtras();




        TextView titlePass = findViewById(R.id.titleContent);
        titlePass.setText(extras.getString("TITLE"));

        TextView linkPass = findViewById(R.id.linkContent);
        linkPass.setText(extras.getString("LINK"));

        TextView descriptionPass = findViewById(R.id.descriptionContent);
        descriptionPass.setText(extras.getString("DESCRIPTION"));


        TextView datePass = findViewById(R.id.pubDateContent);
        datePass.setText(extras.getString("DATEPUBLISHED"));


    }
}
