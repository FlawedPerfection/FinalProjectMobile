package com.example.finalproject;


import static android.media.CamcorderProfile.get;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ListView listViewNews;
    //Making our arraylist to store our News Items from the RSS feed



    Toolbar toolbar;
    private URL CbcNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        toolbar = findViewById(R.id.toolbar);




               }



    }
